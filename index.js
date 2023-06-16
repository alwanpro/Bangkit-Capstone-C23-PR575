import express from 'express';
import bodyParser from 'body-parser';
import dotenv from 'dotenv';
import router from './routes/index.js';

dotenv.config();

const app = express();

app.use(
  bodyParser.json({
    limit: '10mb',
  })
);
app.use(
  bodyParser.urlencoded({
    extended: true,
    limit: '10mb',
  })
);

app.get('/', (req, res) => {
  res.json({ message: 'ok' });
});

app.use('/api', router);
/* Error handler middleware */
app.use((err, req, res, next) => {
  const statusCode = err.statusCode || 500;
  console.error(err.message, err.stack);
  res.status(statusCode).json({ message: err.message });

  return;
});

const port = process.env.PORT;
app.listen(port, '0.0.0.0', () => {
  console.log(`Listening at http://localhost:${port}`);
});
