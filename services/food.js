import fetch from 'node-fetch';
import dotenv from 'dotenv';
import cryptoJs from 'crypto-js';
import { query } from '../configs/database.js';

const { HmacSHA1 } = cryptoJs;

dotenv.config();

const FS_API = 'https://platform.fatsecret.com/rest/server.api';
const RANDOM_STRING = 'asdfpjsdoafosafpoj';
const FS_SECRET = process.env.FS_SECRET;
const FS_KEY = process.env.FS_KEY;

const generateRequestParam = async (rawParams, httpMethod) => {
  const encUrl = encodeURIComponent(FS_API);

  const sortedParams = Object.keys(rawParams)
    .sort()
    .reduce((str, key) => {
      if (str === '') {
        str += `${key}=${rawParams[key]}`;
      } else {
        str += `&${key}=${rawParams[key]}`;
      }

      return str;
    }, '');

  console.log('sorted', sortedParams);

  const encParam = encodeURIComponent(sortedParams);
  const signBaseStr = `${httpMethod}&${encUrl}&${encParam}`;

  console.log('signedstr', signBaseStr);

  const oauthSignature = HmacSHA1(signBaseStr, `${FS_SECRET}&`);
  const encText = cryptoJs.enc.Base64.stringify(oauthSignature);

  console.log('signature', encText);

  const urlParam = {
    ...rawParams,
    oauth_signature: encText,
  };

  const requestParam = Object.keys(urlParam)
    .sort()
    .reduce((str, key) => {
      if (str === '') {
        str += `${key}=${urlParam[key]}`;
      } else {
        str += `&${key}=${urlParam[key]}`;
      }

      return str;
    }, '');

  return requestParam;
};

export const searchFatSecret = async (searchQuery) => {
  const date = new Date();

  const rawParams = {
    format: 'json',
    oauth_consumer_key: FS_KEY,
    oauth_signature_method: 'HMAC-SHA1',
    oauth_timestamp: Math.floor(date.getTime() / 1000),
    oauth_nonce: `${RANDOM_STRING}-${Date.now().toString()}`,
    oauth_version: '1.0',
    method: 'foods.search',
    search_expression: searchQuery,
  };

  const requestParam = await generateRequestParam(rawParams, 'GET');

  console.log('param', requestParam);

  try {
    const response = await fetch(`${FS_API}?${requestParam}`, {
      method: 'GET',
    });

    // console.log('data', response);

    const data = await response.json();

    return data;
  } catch (e) {
    console.log(e);
  }
};

export const scrapeFood = async () => {
  const urlConfig = {
    lang: 'id',
    baseUrl: 'https://www.fatsecret.co.id',
    menuUrl: 'https://www.fatsecret.co.id/kalori-gizi',
    searchUrl: 'https://www.fatsecret.co.id/kalori-gizi/search',
    otherSizes: 'Ukuran Lainnya:',
    caloriesPrefix: 'kkal',
    measurementRegex: {
      carb: /Karb:|g/g,
      protein: /Prot:|g/g,
      fat: /Lemak:|g/g,
      calories: /Kalori:|kkal/g,
    },
  };
};

const getFoodByClass = async (foodData) => {
  const { food_class } = foodData;
  const { rows } = await query(
    'SELECT * FROM foods WHERE food_class = $1 LIMIT 1',
    [food_class]
  );
  if (rows.length == 0) return null;
  return rows[0];
};

const addConsumption = async (consumptionData) => {
  const { foodClass, userId, imageUrl, amount } = consumptionData;

  const { rows: foodRows } = await query(
    'SELECT calorie, default_amount FROM foods WHERE food_class = $1',
    [foodClass]
  );

  if (foodRows.length == 0) throw new Error('Food not found');

  const countCalorie = parseFloat(amount) / foodRows[0].default_amount;
  const totalCalorie = parseFloat(foodRows[0].calorie) * countCalorie;
  console.log('total', totalCalorie);
  try {
    const { rows } = await query(
      'INSERT INTO consumptions (food_class, user_id, image_url, amount, total_calorie) VALUES ($1, $2, $3, $4, $5) RETURNING id',
      [foodClass, userId, imageUrl, amount, totalCalorie]
    );

    if (rows.length == 0) return null;
    return rows[0];
  } catch (error) {
    throw error;
  }
};

const searchFoodByQuery = async (searchData) => {
  const { query, offset, limit } = searchData;
};

const getHistory = async (historyData) => {
  const { offset, limit, fromDate, toDate, user_id } = historyData;

  try {
    const { rows } = await query(
      `
      SELECT DATE_TRUNC('day', created_at) AS date, COUNT(*) AS total_consumptions, SUM(total_calorie) AS sum_calorie, food_class, foods.name, foods.calorie, foods.carb, foods.protein, foods.fat, foods.image_url AS default_image, foods.nutriscore, foods.default_amount, amount, total_calorie
      FROM consumptions
      WHERE user_id = $1
      ${
        fromDate && toDate
          ? `AND created_at > $2 AND created_at < $3`
          : fromDate
          ? `AND created_at > $2`
          : toDate
          ? `AND created_at < $3`
          : ''
      }
      INNER JOIN foods BY food_class = foods.food_class
      GROUP BY DATE_TRUNC('day', created_at)
      ORDER BY date DESC
      ${limit ? 'LIMIT $3' : ''}
      ${offset ? 'OFFSET $4' : ''};`,
      [user_id, fromDate, toDate, limit, offset]
    );
  } catch (err) {
    throw err;
  }

  return rows;
};

const todayCalorie = async (user_id) => {
  const now = new Date();
  const { rows } = await query(
    'SELECT SUM(total_calorie) as daily_calorie FROM consumptions WHERE user_id = $1 AND created_at = $2',
    [user_id, now]
  );

  if (rows.length == 0) return null;

  return rows[0];
};

const getDefaultImage = async (foodClass) => {
  const { rows } = await query(
    'SELECT image_url FROM foods WHERE food_class = $1',
    [foodClass]
  );

  if (rows.length == 0) return null;
  return rows[0];
};
export { getHistory, getFoodByClass, addConsumption, getDefaultImage };
