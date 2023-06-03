import express from 'express';
import userRouter from './user.js';
import foodRouter from './foods.js';
import userDataRouter from './user_data.js';

const router = express.Router();

router.use(userRouter);
router.use(foodRouter);
router.use(userDataRouter);

export default router;
