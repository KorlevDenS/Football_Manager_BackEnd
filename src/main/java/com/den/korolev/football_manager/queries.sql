CREATE TABLE config (
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    login VARCHAR(32) NOT NULL UNIQUE,
    role VARCHAR(10) NOT NULL,
    lang VARCHAR(3),
    theme VARCHAR(10),
    reg_date DATE NOT NULL
);

CREATE TABLE human (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32),
    surname VARCHAR(32),
    patronymic VARCHAR(32),
    birthday DATE,
    sex VARCHAR(1),
    passport_id VARCHAR(32)
);

CREATE TABLE player (
    id_config INTEGER REFERENCES config PRIMARY KEY,
    id_human INTEGER REFERENCES human,
    role VARCHAR(32),
    height DOUBLE PRECISION,
    weight DOUBLE PRECISION
)

