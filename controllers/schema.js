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

export const foodByClassSchema = Joi.object().keys({
  food_class: Joi.string().required(),
});

export const addConsumptionSchema = Joi.object().keys({
  food_class: Joi.string().required(),
  amount: Joi.number().required(),
});
