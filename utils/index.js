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
