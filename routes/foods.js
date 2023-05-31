import express from 'express';
import { searchFood } from '../controllers/food.js';

const foodRouter = express.Router();

foodRouter.route('/food').get(searchFood);

export default foodRouter;
