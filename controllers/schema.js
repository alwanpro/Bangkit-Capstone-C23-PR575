import JoiBase from 'joi';
import JoiDate from '@joi/date';

const Joi = JoiBase.extend(JoiDate);

const registerSchema = Joi.object().keys({
  email: Joi.string()
    .email()
    .required()
    .options({
      messages: {
        'string.base': 'Email is not valid',
        'any.invalidFormat': 'Email format is not valid',
      },
    }),
  name: Joi.string().required(),
  password: Joi.string()
    .required()
    .min(8)
    .options({
      messages: {
        'string.min': 'Password must be at least {#limit} characters long',
      },
    }),
  confirmPassword: Joi.string().required().min(8),
});

const loginSchema = Joi.object().keys({
  email: Joi.string()
    .email()
    .required()
    .options({
      messages: {
        'string.base': 'Email is not valid',
        'any.invalidFormat': 'Email format is not valid',
      },
    }),
  password: Joi.string()
    .required()
    .min(8)
    .options({
      messages: {
        'string.min': 'Password must be at least {#limit} characters long',
      },
    }),
});

const foodByClassSchema = Joi.object().keys({
  food_class: Joi.string().required(),
});

const addConsumptionSchema = Joi.object().keys({
  food_class: Joi.string().required(),
  amount: Joi.number().required(),
});

const userDataSchema = Joi.object().keys({
  weight: Joi.number().required(),
  height: Joi.number().required(),
  gender: Joi.string().valid('male', 'female').required(),
  birth_date: Joi.date().format('YYYY-MM-DD').required(),
});

const updateUserDataSchema = Joi.object().keys({
  weight: Joi.number().required(),
  height: Joi.number().required(),
});

export {
  loginSchema,
  registerSchema,
  foodByClassSchema,
  addConsumptionSchema,
  userDataSchema,
  updateUserDataSchema,
};
