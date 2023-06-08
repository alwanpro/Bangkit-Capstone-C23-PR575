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

// export const updateProfile = async (userData) => {
//   const { weight, height, gender, birth_date, activity, target } = userData;

//   try {
//     const { rows } = await query(
//       'UPDATE profiles SET weight = $1, height = $2, gender = $3, birth_date = $4, activity = $5, target = $6 RETURNING *',
//       [weight, height, gender, birth_date, activity, target]
//     );

//     const updatedProfile = rows[0];
//     return updatedProfile;
//   } catch (error) {
//     throw error;
//   }
// };
