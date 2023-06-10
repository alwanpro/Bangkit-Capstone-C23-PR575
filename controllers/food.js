import { searchFatSecret } from '../services/food.js';

export const searchFood = async (req, res) => {
  const query = 'ayam-geprek';

  try {
    const foodResult = await searchFatSecret(query);
    return res.status(200).json({
      status: 'success',
      message: 'search food',
      data: foodResult,
    });
  } catch (e) {
    return res.status(500);
  }
};
