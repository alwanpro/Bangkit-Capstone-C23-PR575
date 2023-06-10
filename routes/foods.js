import express from 'express';
import * as foodController from '../controllers/food.js';
import { authenticateToken } from '../middleware/auth.js';
import { processFileMiddleware } from '../middleware/file-upload.js';

const foodRouter = express.Router();

// foodRouter.route('/food').get(foodController.searchFood);
foodRouter.route('/foods/search').get();
foodRouter.route('/daily-calorie');
foodRouter
  .route('/foods/:food_class')
  .get(authenticateToken, foodController.getFoodByClass);

foodRouter
  .route('/consumption')
  .post(
    authenticateToken,
    processFileMiddleware,
    foodController.addConsumption
  );

export default foodRouter;
