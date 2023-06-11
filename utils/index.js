import qs from 'node:querystring';

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

  return weightGroup;
};

export { fetchHtml, weightCategory, calculateDailyCalorie };
