import fetch from 'node-fetch';
import dotenv from 'dotenv';
import cryptoJs from 'crypto-js';
import { Cheerio } from 'cheerio';

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
