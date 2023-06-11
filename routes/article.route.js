import express from 'express';
import {
  getArticles,
  getArticleById,
} from '../controllers/article.controller.js';

const articleRouter = express.Router();
articleRouter.route('/article').get(getArticles);
articleRouter.route('/article/:id').get(getArticleById);

export default articleRouter;
