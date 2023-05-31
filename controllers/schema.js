import Joi from 'joi';

export const registerSchema = Joi.object().keys({
  email: Joi.string().email().required(),
  name: Joi.string().required(),
  password: Joi.string().required().min(8),
  confirmPassword: Joi.string().required().min(8),
});

export const loginSchema = Joi.object().keys({
  email: Joi.string().email().required(),
  password: Joi.string().required().min(8),
});
