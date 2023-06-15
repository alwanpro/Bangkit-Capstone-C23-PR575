import qs from 'node:querystring';
import { nutriScore } from 'nutri-score';
import csv from 'csv-parser';
import fs from 'fs';

import { query } from '../configs/database.config.js';

const fetchHtml = async (uri, options) => {
  const headers = {
    'cache-control': 'no-cache',
    'user-agent':
      'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36',
  };

  const params = qs.stringify(options, {
    arrayFormat: 'bracket',
    encode: false,
  });

  const url = `${uri}?${params}`;
  const res = await got.get(url, { headers, responseType: 'text' });
  const result = await fetch(url, {
    method: 'GET',
    headers,
  });

  const data = await result.text();

  return data;
};

//Calculate daily calorie
const calculateDailyCalorie = async (userData) => {
  const { weight, height, gender, birth_date } = userData;

  const currentDate = new Date();
  const ageInMillis = currentDate - new Date(birth_date);
  const ageInYears = Math.floor(ageInMillis / (1000 * 60 * 60 * 24 * 365.25));

  let dailyCalorie;
  if (gender === 'male') {
    //male
    dailyCalorie = Math.round(10 * weight + 6.25 * height - 5 * ageInYears + 5);
  } else if (gender === 'female') {
    //female
    dailyCalorie = Math.round(
      10 * weight + 6.25 * height - 5 * ageInYears - 161
    );
  } else {
    dailyCalorie = 0;
  }

  return dailyCalorie;
};

//weight category
const weightCategory = async (userData) => {
  const { weight, height } = userData;

  const heightInMeters = height / 100;
  const calculateBMI = weight / heightInMeters ** 2;

  let weightGroup;
  if (calculateBMI < 18.5) {
    weightGroup = 'Underweight';
  } else if (18.5 <= calculateBMI && calculateBMI < 24.9) {
    weightGroup = 'Normal';
  } else if (25.0 <= calculateBMI && calculateBMI < 29.9) {
    weightGroup = 'Overweight';
  } else {
    weightGroup = 'Obese';
  }

  return { weightGroup, calculateBMI };
};

const getAge = async (dateString) => {
  let today = new Date();
  let birthDate = new Date(dateString);
  let age = today.getFullYear() - birthDate.getFullYear();
  let m = today.getMonth() - birthDate.getMonth();
  if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
    age--;
  }
  return age;
};

const calculateNutriscore = () => {
  const results = [];
  fs.createReadStream('docs/data_makanan.csv')
    .pipe(csv())
    .on('data', (data) => results.push(data))
    .on('end', () => {
      results.forEach(async (result) => {
        const nutriscore = nutriScore.calculateClass(
          {
            energy: result.calorie * 4.18,
            fibers: result.fibers,
            fruit_percentage: 0,
            proteins: result.protein,
            saturated_fats: result.saturated_fats,
            sodium: result.sodium,
            sugar: result.sugar,
          },
          'solid'
        );

        result['nutriscore'] = nutriscore;

        await query(
          'INSERT INTO foods (name, food_class, calorie, carb, protein, fat, image_url, default_amount, nutriscore) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9) ON CONFLICT (food_class) DO UPDATE SET name = excluded.name, food_class = excluded.food_class, calorie = excluded.calorie, carb = excluded.carb, protein = excluded.protein, fat = excluded.fat, image_url = excluded.image_url, default_amount = excluded.default_amount, nutriscore = excluded.nutriscore',
          [
            result['name'],
            result['food_class'],
            result['calorie'],
            result['carb'],
            result['protein'],
            result['fat'],
            result['image_url'],
            result['default_amount'],
            result['nutriscore'],
          ]
        );
      });

      return;
    });

  return;
};

export {
  fetchHtml,
  weightCategory,
  calculateDailyCalorie,
  getAge,
  calculateNutriscore,
};
