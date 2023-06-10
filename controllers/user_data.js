import { calculateDailyCalorie, weightCategory } from '../utils/index.js';
import { createProfile } from '../services/user_data.js';

export const createUserData = async (req, res) => {
  const { weight, height, gender, birth_date } = req.body;

  // Calculate daily calory by user data 
  const target = await calculateDailyCalorie(req.body);
  const weight_category = weightCategory(req.body);

  const userData = {
    weight: weight,
    height: height,
    gender: gender,
    birth_date: birth_date,
    target: target,
    weight_category: weight_category,
  };

  try {
    const createdProfile = await createProfile(userData);

    res.json({
      message: 'create user-data success',
      status: 'success',
      data: createdProfile
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({
      message: 'Failed to create user-data',
      status: 'error',
      error: error.message
    });
  }
};







