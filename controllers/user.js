import { registerSchema, loginSchema } from './schema.js';
import {
  addUser,
  getUsersByEmail,
  createProfile,
  updateProfile,
} from '../services/user.js';
import bcrypt from 'bcrypt';
import { calculateDailyCalorie, weightCategory } from '../utils/index.js';
import { generateToken } from '../middleware/auth.js';

export const protectedRoutes = async (req, res) => {
  res.status(200).json({
    status: 'success',
    message: 'Protected routes',
    data: req.user,
  });
};

export const login = async (req, res) => {
  const { email, password } = req.body;

  try {
    await loginSchema.validateAsync({ email, password });
  } catch (e) {
    return res.status(422).json({
      status: 'error',
      message: 'Invalid request data',
      errors: e?.details,
    });
  }

  const user = await getUsersByEmail(email);

  if (!user) {
    return res.status(404).json({
      status: 'error',
      message: "User doesn't exist",
    });
  }

  const validPassword = await bcrypt.compare(password, user.password);

  if (!validPassword) {
    return res.status(403).json({
      status: 'error',
      message: 'Incorrect passsword',
    });
  }

  const token = await generateToken(user.id, user.email);

  return res.status(200).json({
    status: 'success',
    message: 'Login success',
    data: {
      userId: user.id,
      token,
    },
  });
};

export const register = async (req, res) => {
  const data = req.body;

  try {
    await registerSchema.validateAsync(data);

    if (data.password !== data.confirmPassword) {
      return res.status(400).json({
        status: 'error',
        message: "Password doesn't match",
      });
    }

    const userExist = await getUsersByEmail(data.email);

    if (!!userExist) {
      res.status(400).json({
        status: 'error',
        message: 'Duplicated email',
      });
    }
    const newUser = await addUser(data);

    const { id, email } = newUser;
    const token = await generateToken(id, email);

    return res.status(200).json({
      status: 'success',
      message: 'Register success',
      data: {
        userId: id,
        token,
      },
    });
  } catch (e) {
    if (e?.isJoi) {
      return res.status(422).json({
        status: 'error',
        message: 'Invalid request data',
        errors: e?.details,
      });
    }

    return res.status(400).json({
      status: 'error',
      message: 'Bad request',
    });
  }
};

export const createUserData = async (req, res) => {
  const { weight, height, gender, birth_date } = req.body;

  // Menghitung kalori harian berdasarkan data pengguna
  const target = await calculateDailyCalorie(req.body);
  const weight_category = await weightCategory(req.body);

  const userData = {
    weight,
    height,
    gender,
    birth_date,
    target,
    userId: req.user.userId,
    weight_category,
  };

  try {
    const createdProfile = await createProfile(userData);

    res.json({
      message: 'create user-data success',
      status: 'success',
      data: createdProfile,
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({
      message: 'Failed to create user-data',
      status: 'error',
    });
  }
};

export const updateUserData = async (req, res) => {
  const { weight, height } = req.body;
  const { userId } = req.user;

  try {
    const updatedProfile = await updateProfile({
      weight,
      height,
      userId,
    });

    if (updateProfile) {
      return res.json({
        message: 'update user data success',
        status: 'success',
        data: updatedProfile,
      });
    }

    return res.json({
      message: 'failed to update',
      status: 'error',
    });
  } catch (err) {
    console.log(err);
    res.status(500).json({
      message: 'Failed to update user data',
      status: 'error',
    });
  }
};
