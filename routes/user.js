import express from 'express';
import { login, protectedRoutes, register } from '../controllers/user.js';
import { authenticateToken } from '../middleware/auth.js';

const userRouter = express.Router();

userRouter.route('/login').post(login);
userRouter.route('/register').post(register);
userRouter.route('/user/test').post(authenticateToken, protectedRoutes);

export default userRouter;
