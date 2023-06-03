import { query } from '../configs/database.js';

export const createProfile = async (userData) => {
  const { weight, height, gender, birth_date, activity, target } = userData;

  try {
    const { rows } = await query(
      'INSERT INTO profiles (weight, height, gender, birth_date, activity, target) VALUES ($1, $2, $3, $4, $5, $6)',
      [weight, height, gender, birth_date, activity, target]
    );

    return userData;
  } catch (error) {
    throw error;
  }
};
