import jwt from 'jsonwebtoken';

export const authenticateToken = async (req, res, next) => {
  const authHeader = req.headers.authorization;
  const jwtKey = process.env.JWT_KEY;

  if (!authHeader)
    return res.status(401).json({
      status: 'error',
      message: 'Unauthorized access',
    });
  if (authHeader) {
    const token = authHeader.split(' ')[1];

    jwt.verify(token, jwtKey, (err, user) => {
      if (err) {
        return res.status(401).json({
          status: 'error',
          message: 'Unauthorized access',
        });
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
