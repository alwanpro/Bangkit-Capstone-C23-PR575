import express from 'express';
import * as userController from '../controllers/user.controller.js';
import { authenticateToken } from '../middleware/auth.js';

const userRouter = express.Router();

userRouter.route('/login').post(userController.login);
userRouter.route('/register').post(userController.register);
userRouter
  .route('/profile')
  .get(authenticateToken, userController.getUserProfile);
userRouter
  .route('/profile')
  .post(authenticateToken, userController.createUserData);
userRouter
  .route('/profile')
  .put(authenticateToken, userController.updateUserData);

userRouter.route('/bmi').get(authenticateToken, userController.getBMI);

export default userRouter;
