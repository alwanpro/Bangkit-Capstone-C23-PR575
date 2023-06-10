import express from 'express';
import {
  login,
  protectedRoutes,
  register,
  createUserData,
} from '../controllers/user.js';
import { authenticateToken } from '../middleware/auth.js';

const userRouter = express.Router();

userRouter.route('/login').post(login);
userRouter.route('/register').post(register);
userRouter.route('/register').post(register);
userRouter.route('/user_data').post(authenticateToken, createUserData);

export default userRouter;
