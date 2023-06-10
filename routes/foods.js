import express from 'express';
import * as foodController from '../controllers/food.js';
import { authenticateToken } from '../middleware/auth.js';

const foodRouter = express.Router();

// foodRouter.route('/food').get(foodController.searchFood);
foodRouter.route('/food/search').get();
foodRouter.route('/daily-calorie')
foodRouter.route('/food').get(authenticateToken, foodController.getFoodByClass);

foodRouter.route('/consumption').post();

export default foodRouter;
