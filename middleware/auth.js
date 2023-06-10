import jwt from 'jsonwebtoken';

export const authenticateToken = async (req, res, next) => {
  const authHeader = req.headers.authorization;
  const jwtKey = process.env.JWT_KEY;

  if (!authHeader) res.sendStatus(401);
  if (authHeader) {
    const token = authHeader.split(' ')[1];

    jwt.verify(token, jwtKey, (err, user) => {
      if (err) {
        return res.sendStatus(403);
      }

      req.user = {
        userId: user.userId,
        email: user.email,
      };

      next();
    });
  }
};

export const generateToken = async (userId, email) => {
  const jwtKey = process.env.JWT_KEY;
  const accessToken = jwt.sign({ userId, email }, jwtKey);

  return accessToken;
};
