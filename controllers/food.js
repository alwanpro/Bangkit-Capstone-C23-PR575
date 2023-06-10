import * as foodService from '../services/food.js';
import { uploadFile } from '../services/storage.js';
import { addConsumptionSchema, foodByClassSchema } from './schema.js';

export const searchFood = async (req, res) => {
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
  const data = req.params;

  // Validate schema
  try {
    await foodByClassSchema.validateAsync(data);
  } catch (e) {
    return res.status(422).json({
      status: 'error',
      message: 'Invalid request data',
      errors: e?.details,
    });
  }

  // Fetch to service
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
};

const addConsumption = async (req, res) => {
  const data = req.body;
  const user = req.user;

  // Validate schema
  try {
    await addConsumptionSchema.validateAsync(data);
  } catch (err) {
    return res.status(422).json({
      status: 'error',
      message: 'Invalid request data',
      errors: e?.details,
    });
  }

  try {
    let imageUrl = '';

    if (req.file) {
      imageUrl = await uploadFile(req.file, user.userId, data.food_class);
    } else {
      imageUrl = await foodService.getDefaultImage(data.food_class);
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
    console.log(err);
    return res.sendStatus(400);
  }
};

export { getFoodByClass, addConsumption };
