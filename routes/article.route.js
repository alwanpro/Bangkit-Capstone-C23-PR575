import express from 'express';
import {
    getArticles,
    getArticleById,
} from '../controllers/article.controller.js';
import { authenticateToken } from '../middleware/auth.js';

const articleRouter = express.Router();
articleRouter.route('/article').get(authenticateToken, getArticles);
articleRouter.route('/article/:id').get(authenticateToken, getArticleById);

export default articleRouter;
