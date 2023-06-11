import { query } from '../configs/database.js';
import bcrypt from 'bcrypt';
import { calculateDailyCalorie, weightCategory } from '../utils/index.js';

const saltRounds = 10;

export const getUsersByEmail = async (email) => {
  const { rows } = await query('SELECT * FROM users WHERE email = $1 LIMIT 1', [
    email,
  ]);

  if (rows.length == 0) return null;
  return rows[0];
};

export const addUser = async (user) => {
  const { name, email, password } = user;
  const hashedPassword = await bcrypt.hash(password, saltRounds);

  const { rows } = await query(
    'INSERT INTO users(name, email, password) VALUES ($1, $2, $3) RETURNING id, email',
    [name, email, hashedPassword]
  );

  if (rows.length == 0) return null;
  return rows[0];
};

export const createProfile = async (userData) => {
  const {
    weight,
    height,
    gender,
    birth_date,
    target,
    userId,
    weight_category,
  } = userData;

  try {
    const { rows } = await query(
      'INSERT INTO profiles (user_id, weight, height, gender, birth_date, target, weight_category) VALUES ($1, $2, $3, $4, $5, $6, $7)',
      [userId, weight, height, gender, birth_date, target, weight_category]
    );

    return userData;
  } catch (error) {
    throw error;
  }
};

export const getUserProfile = async (userId) => {
  const { rows } = await query('SELECT * FROM profiles WHERE user_id = $1', [
    userId,
  ]);

  return rows[0];
};

export const updateProfile = async (newData) => {
  const { weight, height, userId } = newData;

  const userProfile = await getUserProfile(userId);

  const { gender, birth_date } = userProfile;

  const newTarget = await calculateDailyCalorie({
    weight,
    height,
    gender,
    birth_date,
  });

  const newBMI = await weightCategory({ weight, height, gender, birth_date });

  try {
    const { rows } = await query(
      'UPDATE profiles SET weight = $1, height = $2, target = $3, weight_category = $4 WHERE user_id = $5 RETURNING *',
      [weight, height, newTarget, newBMI, userId]
    );

    return rows[0];
  } catch (err) {
    throw new Error('Failed to update');
  }
};

