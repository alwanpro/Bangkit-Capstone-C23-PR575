import express from 'express';
import userRouter from './user.route.js';
import foodRouter from './foods.route.js';
// import userDataRouter from './user_data.js';
import articleRouter from './article.route.js';

const router = express.Router();

router.use(userRouter);
router.use(foodRouter);
// router.use(userDataRouter);
router.use(articleRouter);

export default router;
