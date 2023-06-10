import express from 'express';
import { createUserData } from '../controllers/user_data.js';

const userDataRouter = express.Router(); 

userDataRouter.route('/user_data').post(createUserData);

export default userDataRouter;