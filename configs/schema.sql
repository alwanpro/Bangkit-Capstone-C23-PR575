CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SET TIME ZONE 'Asia/Jakarta';

CREATE TABLE IF NOT EXISTS users (
    id uuid DEFAULT uuid_generate_v4(),
    name VARCHAR (100) NOT NULL,
    email VARCHAR (100) UNIQUE NOT NULL,
    password VARCHAR (100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS profiles (
    id uuid DEFAULT uuid_generate_v4(),
    user_id uuid,
    weight INT NOT NULL,
    height INT NOT NULL,
    gender INT NOT NULL,
    birth_date DATE NOT NULL,
    -- activity VARCHAR (100) NOT NULL,
    target VARCHAR (100) NOT NULL,
    weight_category VARCHAR (100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS foods (
    id uuid DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    food_class VARCHAR(100) UNIQUE,
    calorie FLOAT NOT NULL,
    carb FLOAT NOT NULL,
    protein FLOAT NOT NULL,
    fat FLOAT NOT NULL,
    image_url VARCHAR (800) NOT NULL,
    nutriscore CHAR,
    default_amount INT NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS consumptions (
    id uuid DEFAULT uuid_generate_v4(),
    food_class VARCHAR(100) NOT NULL,
    user_id uuid,
    image_url VARCHAR(100),
    amount INT NOT NULL,
    total_calorie INT NOT NULL,
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
   
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (food_class) REFERENCES foods (food_class)
);

CREATE INDEX idx_consumption_history ON consumptions(created_at);
CREATE INDEX idx_food_class ON foods(food_class);
