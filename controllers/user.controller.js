import {
  registerSchema,
  loginSchema,
  userDataSchema,
  updateUserDataSchema,
} from './schema.js';
import * as userService from '../services/user.service.js';
import bcrypt from 'bcrypt';
import {
  calculateDailyCalorie,
  getAge,
  weightCategory,
} from '../utils/index.js';
import { generateToken } from '../middleware/auth.js';

const protectedRoutes = async (req, res) => {
  res.status(200).json({
    status: 'success',
    message: 'Protected routes',
    data: req.user,
  });
};

const login = async (req, res) => {
  const { email, password } = req.body;

  try {
    // validate login schema
    await loginSchema.validateAsync({ email, password });

    const user = await userService.getUsersByEmail(email);

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
  } catch (e) {
    if (e?.isJoi) {
      return res.status(422).json({
        status: 'error',
        message: 'Invalid request data',
        errors: e?.details,
      });
    }

    console.error(e);

    return res.status(400).json({
      status: 'error',
      message: 'Failed to login',
      errors: e.message,
    });
  }
};

const register = async (req, res) => {
  try {
    const data = req.body;

    await registerSchema.validateAsync(data);

    if (data.password !== data.confirmPassword) {
      return res.status(400).json({
        status: 'error',
        message: "Password doesn't match",
      });
    }

    const userExist = await userService.getUsersByEmail(data.email);

    if (!!userExist) {
      return res.status(400).json({
        status: 'error',
        message: 'Duplicated email',
      });
    }
    const newUser = await userService.addUser(data);

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
        message: e.message,
        errors: e?.details,
      });
    }

    console.error(e);
    return res.status(400).json({
      status: 'error',
      message: 'Failed to register',
      errors: e.message,
    });
  }
};

const createUserData = async (req, res) => {
  try {
    await userDataSchema.validateAsync(req.body);
    const { weight, height, gender, birth_date } = req.body;
    // Menghitung kalori harian berdasarkan data pengguna
    const [target, bmi] = await Promise.all([
      calculateDailyCalorie(req.body),
      weightCategory(req.body),
    ]);

    const userData = {
      weight,
      height,
      gender,
      birth_date,
      target,
      userId: req.user.userId,
      weight_category: bmi.weightGroup,
    };

    const createdProfile = await userService.createProfile(userData);

    return res.status(200).json({
      status: 'success',
      message: 'create user-data success',
      data: {
        ...createdProfile,
        target: parseInt(createdProfile.target),
      },
    });
  } catch (error) {
    if (error?.isJoi) {
      return res.status(422).json({
        status: 'error',
        message: error.message,
        errors: error?.details,
      });
    }
    console.error(error);
    return res.status(500).json({
      status: 'error',
      message: 'Failed to create user-data',
      errors: error.message,
    });
  }
};

const updateUserData = async (req, res) => {
  try {
    const { weight, height } = req.body;
    const { userId } = req.user;

    await updateUserDataSchema.validateAsync(req.body);

    const updatedProfile = await userService.updateProfile({
      weight,
      height,
      userId,
    });

    if (updatedProfile) {
      return res.status(200).json({
        status: 'success',
        message: 'update user data success',
        data: {
          ...updatedProfile,
          target: parseInt(updatedProfile.target),
          age: await getAge(updatedProfile.birth_date),
        },
      });
    }

    return res.status(400).json({
      status: 'error',
      message: 'failed to update',
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
    res.status(500).json({
      status: 'error',
      message: 'Failed to update user data',
      errors: err.message,
    });
  }
};

const getUserProfile = async (req, res) => {
  try {
    const { userId } = req.user;

    const userProfile = await userService.getUserProfile(userId);

    if (userProfile) {
      return res.status(200).json({
        status: 'success',
        message: 'fetch user data success',
        data: {
          ...userProfile,
          age: await getAge(userProfile.birth_date),
        },
      });
    }

    return res.status(400).json({
      status: 'error',
      message: 'failed to fetch',
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({
      status: 'error',
      message: 'Failed to fetch user profile',
      errors: err.message,
    });
  }
};

const getBMI = async (req, res) => {
  try {
    const { userId } = req.user;

    const userProfile = await userService.getUserProfile(userId);
    if (userProfile) {
      const bmi = await weightCategory(userProfile);

      return res.status(200).json({
        status: 'success',
        message: 'fetch bmi success',
        data: {
          weight_category: bmi.weightGroup,
          calculated_bmi: bmi.calculateBMI,
        },
      });
    }

    return res.status(400).json({
      status: 'error',
      message: 'failed to fetch',
    });
  } catch (err) {
    console.error(err);
    res.status(500).json({
      status: 'error',
      message: 'Failed to fetch user profile',
      errors: err.message,
    });
  }
};

export {
  protectedRoutes,
  login,
  register,
  createUserData,
  updateUserData,
  getUserProfile,
  getBMI,
};
