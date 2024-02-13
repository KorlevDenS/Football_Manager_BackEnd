CREATE TABLE config (
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    login VARCHAR(32) NOT NULL UNIQUE,
    role VARCHAR(10) NOT NULL,
    reg_date DATE NOT NULL
);

CREATE TABLE human (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(32),
                       surname VARCHAR(32),
                       patronymic VARCHAR(32),
                       birthday DATE,
                       sex VARCHAR(1),
                       passport_id VARCHAR(32) UNIQUE
);

CREATE TABLE player (
    id_config INTEGER REFERENCES config PRIMARY KEY,
    id_human INTEGER REFERENCES human,
    role VARCHAR(32),
    height DOUBLE PRECISION,
    weight DOUBLE PRECISION
);


CREATE TABLE collective_event (
    id SERIAL PRIMARY KEY,
    type VARCHAR(32),
    location VARCHAR(64),
    date DATE,
    time TIME,
    duration TIME,
    description VARCHAR(100)
);

CREATE TABLE custom (
    id SERIAL PRIMARY KEY,
    id_collective_event INTEGER REFERENCES collective_event,
    name VARCHAR(32)
);

CREATE TABLE player_custom (
    id SERIAL PRIMARY KEY,
    id_player INTEGER REFERENCES player,
    id_custom INTEGER REFERENCES custom,
    what_liked VARCHAR(100),
    what_disliked VARCHAR(100),
    what_to_improve VARCHAR(100),
    comments VARCHAR(100)
);

CREATE TABLE training (
    id SERIAL PRIMARY KEY,
    id_collective_event INTEGER REFERENCES collective_event,
    type VARCHAR(32),
    players_amount INTEGER,
    field_format VARCHAR(32)
);

CREATE TABLE player_training (
    id SERIAL PRIMARY KEY,
    id_player INTEGER REFERENCES player,
    id_training INTEGER REFERENCES training,
    goals INTEGER,
    what_liked VARCHAR(100),
    what_disliked VARCHAR(100),
    what_to_improve VARCHAR(100),
    comments VARCHAR(100)
);

CREATE TABLE match (
    id SERIAL PRIMARY KEY,
    id_collective_event INTEGER REFERENCES collective_event,
    team1 VARCHAR(32),
    team2 VARCHAR(32),
    team1_goals INTEGER,
    team2_goals INTEGER,
    field_format VARCHAR(32),
    result VARCHAR(4)
);

CREATE TABLE player_match (
    id SERIAL PRIMARY KEY,
    id_player INTEGER REFERENCES player,
    id_match INTEGER REFERENCES match,
    goals INTEGER,
    field_time TIME,
    role VARCHAR(32),
    assists INTEGER,
    what_liked VARCHAR(100),
    what_disliked VARCHAR(100),
    what_to_improve VARCHAR(100),
    comments VARCHAR(100)
);

create table exercise (
    id SERIAL PRIMARY KEY,
    id_player INTEGER REFERENCES player,
    title VARCHAR(64),
    technic VARCHAR,
    photoLink VARCHAR,
    videoLink VARCHAR,
    duration TIME,
    amount INTEGER,
    muscle_load VARCHAR(64),
    equipment VARCHAR(100),
    min_people INTEGER,
    usage_count INTEGER,
    date DATE NOT NULL
);

create table training_target (
    id SERIAL PRIMARY KEY,
    id_training INTEGER REFERENCES training,
    id_exercise INTEGER REFERENCES exercise
);