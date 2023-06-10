import { query } from '../configs/database.js';
import bcrypt from 'bcrypt';

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
