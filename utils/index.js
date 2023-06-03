import qs from 'node:querystring';

export const validateEmail = (email) => {
  return email;
};

export const hashPassword = (password) => {
  return password;
};

export const fetchHtml = async (uri, options) => {
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

  console.log('data', data);

  return data;
};

//Calculate daily calorie
export const calculateDailyCalorie = (userData) => {
  const { weight, height, gender, birth_date } = userData;

  const currentDate = new Date();
  const ageInMillis = currentDate - new Date(birth_date);
  const ageInYears = Math.floor(ageInMillis / (1000 * 60 * 60 * 24 * 365.25));

  let dailyCalorie;
  if (gender === '1') {  //male
    dailyCalorie = Math.round(10 * weight + 6.25 * height - 5 * ageInYears + 5);
  } else if (gender === '0') {  //female
    dailyCalorie = Math.round(10 * weight + 6.25 * height - 5 * ageInYears - 161);
  } else {
    dailyCalorie = 0;
  }

  return dailyCalorie;
};

