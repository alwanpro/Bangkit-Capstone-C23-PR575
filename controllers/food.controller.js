import * as foodService from '../services/food.service.js';
import { uploadFile } from '../services/storage.service.js';
import { addConsumptionSchema, foodByClassSchema } from './schema.js';
import moment from 'moment-timezone';

const searchFood = async (req, res) => {
  const query = 'ayam_geprek';

  try {
    const foodResult = await foodService.searchFatSecret(query);
    return res.status(200).json({
      status: 'success',
      message: 'search food',
      data: foodResult,
    });
  } catch (e) {
    return res.status(500);
  }
};

const getFoodByClass = async (req, res) => {
  // Validate schema
  try {
    const data = req.params;
    await foodByClassSchema.validateAsync(data);
    const food = await foodService.getFoodByClass(data);

    if (!food)
      return res.status(404).json({
        status: 'error',
        message: 'Food not found',
      });

    return res.status(200).json({
      status: 'success',
      message: 'Food found',
      data: food,
    });
  } catch (e) {
    if (e?.isJoi) {
      return res.status(422).json({
        status: 'error',
        message: e.message,
        errors: e?.details,
      });
    }
    console.error(e);
    return res.status(400).json({
      status: 'error',
      message: 'Invalid request data',
      errors: e?.details,
    });
  }

  // Fetch to service
};

const addConsumption = async (req, res) => {
  try {
    const data = req.body;
    const user = req.user;

    // Validate schema
    await addConsumptionSchema.validateAsync(data);

    let imageUrl = '';

    if (data.file) {
      imageUrl = await uploadFile(data.file, user.userId, data.food_class);
    } else {
      imageUrl = (await foodService.getDefaultImage(data.food_class)).image_url;
    }

    const consumption = await foodService.addConsumption({
      imageUrl,
      userId: user.userId,
      foodClass: data.food_class,
      amount: data.amount,
    });

    if (!consumption)
      return res.status(404).json({
        status: 'error',
        message: 'Invalid data',
      });

    return res.status(200).json({
      status: 'success',
      message: 'Consumption added',
      data: consumption,
    });
  } catch (err) {
    if (err?.isJoi) {
      return res.status(422).json({
        status: 'error',
        message: err.message,
        errors: err?.details,
      });
    }
    console.error(err);
    return res.status(400).json({
      status: 'error',
      message: 'Failed to add consumptions',
      errors: err?.message,
    });
  }
};

const getTodayCalorie = async (req, res) => {
  try {
    const todayData = await foodService.todayCalorie(req.user.userId);

    if (!todayData) {
      return res.status(404).json({
        status: 'error',
        message: 'User profile not found',
      });
    }
    return res.status(200).json({
      status: 'success',
      message: 'Daily calorie',
      data: todayData,
    });
  } catch (err) {
    console.error(err);
    return res.status(400).json({
      status: 'error',
      message: 'Failed to get today calorie',
      errors: err?.message,
    });
  }
};

const getFoods = async (req, res) => {
  try {
    const query = req.query;
    const foods = await foodService.searchFoodByQuery(query);
    return res.status(200).json({
      status: 'success',
      message: 'Foods data fetched',
      data: foods,
      metadata: {
        page: req.query.page ?? 1,
        limit: req.query.limit ?? 100,
      },
    });
  } catch (err) {
    console.error(err);
    return res.status(400).json({
      status: 'error',
      message: 'Failed to get today calorie',
      errors: err?.message,
    });
  }
};

// todo implement best practice and pagination
const getConsumptionHistory = async (req, res) => {
  try {
    const { userId } = req.user;
    const consumptions = await foodService.getUserConsumptions({
      ...req.query,
      userId,
    });

    const rawHistory = consumptions.reduce((acc, curr) => {
      const targetTimezone = 'Asia/Bangkok';
      const timestamp = curr.created_at;
      const utc7Timestamp = moment(timestamp).tz(targetTimezone).format();
      const date = utc7Timestamp.toString().split('T')[0];

      if (acc[date]) {
        acc[date].push(curr);
      } else {
        acc[date] = [curr];
      }

      return acc;
    }, {});

    const history = Object.keys(rawHistory).map((date) => {
      return {
        date,
        consumptions: rawHistory[date],
      };
    });

    return res.status(200).json({
      status: 'success',
      message: 'History retrieved',
      data: history,
      metadata: {
        page: req.query.page ?? 1,
        limit: req.query.limit ?? 100,
      },
    });
  } catch (err) {
    console.error(err);
    return res.status(400).json({
      status: 'error',
      message: 'Failed to get today calorie',
      errors: err?.message,
    });
  }
};

export {
  searchFood,
  getFoodByClass,
  addConsumption,
  getTodayCalorie,
  getFoods,
  getConsumptionHistory,
};
