import * as foodService from '../services/food.js';
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

  try {
    await addConsumptionSchema.validateAsync(data);
  } catch (e) {
    return res.status(422).json({
      status: 'error',
      message: 'Invalid request data',
      errors: e?.details,
    });
  }

  const consumption = await foodService.addConsumption(data);

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
};

export { getFoodByClass };
