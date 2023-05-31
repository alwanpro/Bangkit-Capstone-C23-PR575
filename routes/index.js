import express from 'express';
import userRouter from './user.js';
import foodRouter from './foods.js';

const router = express.Router();

router.use(userRouter);
router.use(foodRouter);

export default router;
