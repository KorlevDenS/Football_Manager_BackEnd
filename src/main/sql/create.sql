CREATE TABLE human (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(32) NOT NULL,
                       surname VARCHAR(32) NOT NULL,
                       patronymic VARCHAR(32),
                       birthday DATE NOT NULL,
                       sex VARCHAR(1) NOT NULL,
                       passport_id VARCHAR(32) UNIQUE
);

create index human_pk on human (id);

CREATE TABLE config (
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(32) NOT NULL UNIQUE,
                        password VARCHAR(64) NOT NULL,
                        login VARCHAR(32) NOT NULL UNIQUE,
                        role VARCHAR(10) NOT NULL,
                        reg_date DATE NOT NULL
);

create index config_pk on config(id);
create index config_username on config(username);
create index config_login on config(password);

CREATE TABLE player (
                        id_config INTEGER REFERENCES config PRIMARY KEY,
                        id_human INTEGER REFERENCES human NOT NULL,
                        role VARCHAR(32),
                        height DOUBLE PRECISION,
                        weight DOUBLE PRECISION
);

create index player_pk on player(id_config);
create index player_human on player(id_human);

CREATE TABLE collective_event (
                                  id SERIAL PRIMARY KEY,
                                  type VARCHAR(32),
                                  location VARCHAR(64),
                                  date DATE,
                                  time TIME,
                                  duration TIME,
                                  description VARCHAR(100)
);

create index collective_event_pk on collective_event(id);
create index collective_event_date on collective_event(date);

CREATE TABLE match (
                       id SERIAL PRIMARY KEY,
                       id_collective_event INTEGER REFERENCES collective_event on DELETE cascade,
                       team1 VARCHAR(32),
                       team2 VARCHAR(32),
                       team1_goals INTEGER,
                       team2_goals INTEGER,
                       field_format VARCHAR(32),
                       result VARCHAR(4)
);

create index match_pk on match(id);
create index match_event on match(id_collective_event);

CREATE TABLE custom (
                        id SERIAL PRIMARY KEY,
                        id_collective_event INTEGER REFERENCES collective_event on delete cascade,
                        name VARCHAR(32)
);

create index custom_pk on custom(id);
create index custom_event on custom(id_collective_event);

CREATE TABLE training (
                          id SERIAL PRIMARY KEY,
                          id_collective_event INTEGER REFERENCES collective_event on DELETE cascade,
                          type VARCHAR(32),
                          players_amount INTEGER,
                          field_format VARCHAR(32)
);

create index training_pk on training(id);
create index training_event on training(id_collective_event);

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

create index player_match_pk on player_match(id);
create index player_match_player on player_match(id_player);
create index player_match_match on player_match(id_match);

CREATE TABLE player_custom (
                               id SERIAL PRIMARY KEY,
                               id_player INTEGER REFERENCES player,
                               id_custom INTEGER REFERENCES custom,
                               what_liked VARCHAR(100),
                               what_disliked VARCHAR(100),
                               what_to_improve VARCHAR(100),
                               comments VARCHAR(100)
);

create index player_custom_pk on player_custom(id);
create index player_custom_player on player_custom(id_player);
create index player_custom_custom on player_custom(id_custom);

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

create index player_training_pk on player_training(id);
create index player_training_player on player_training(id_player);
create index player_training_training on player_training(id_training);

create table exercise (
                          id SERIAL PRIMARY KEY,
                          id_player INTEGER REFERENCES player on delete cascade,
                          title VARCHAR(64),
                          technic VARCHAR,
                          photo_link VARCHAR,
                          video_link VARCHAR,
                          duration TIME,
                          amount INTEGER,
                          muscle_load VARCHAR(64),
                          equipment VARCHAR(100),
                          min_people INTEGER,
                          usage_count INTEGER,
                          date DATE NOT NULL
);

create index exercise_pk on exercise(id);
create index exercise_owner on exercise(id_player);

create table training_target (
                                 id SERIAL PRIMARY KEY,
                                 id_training INTEGER REFERENCES training on delete cascade,
                                 id_exercise INTEGER REFERENCES exercise on delete cascade
);

create index training_target_pk on training_target(id);
create index training_target_training on training_target(id_training);
create index training_target_exercise on training_target(id_exercise);

create table club (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    foundation_date DATE NOT NULL,
    reg_date DATE NOT NULL,
    id_founder INTEGER REFERENCES human NOT NULL ,
    location VARCHAR(100) NOT NULL,
    short_name VARCHAR(32),
    description VARCHAR(300) NOT NULL DEFAULT ''
);

create index club_pk on club(id);
create index club_founder on club(id_founder);

create table application (
    id SERIAL PRIMARY KEY,
    id_player INTEGER REFERENCES player ON DELETE CASCADE NOT NULL ,
    id_club INTEGER REFERENCES club ON DELETE CASCADE NOT NULL ,
    player_approve INTEGER NOT NULL CHECK ( player_approve >= 0 AND player_approve <= 2),
    club_approve INTEGER NOT NULL CHECK ( club_approve >= 0 AND club_approve <= 2),
    creation_date DATE NOT NULL ,
    message VARCHAR(100)
);

create index application_pk on application(id);
create index application_player on application(id_player);
create index application_club on application(id_club);
create index application_player_approve on application(player_approve);
create index application_club_approve on application(club_approve);

create table club_management (
    id SERIAL PRIMARY KEY,
    id_config INTEGER REFERENCES config ON DELETE CASCADE NOT NULL ,
    id_club INTEGER REFERENCES club ON DELETE CASCADE NOT NULL ,
    role VARCHAR(32)
);

create index club_management_manager on club_management(id_config);
create index club_management_club on club_management(id_club);

create table club_membership (
    id SERIAL PRIMARY KEY,
    id_player INTEGER REFERENCES player ON DELETE CASCADE NOT NULL ,
    id_club INTEGER REFERENCES club ON DELETE CASCADE NOT NULL ,
    begin_date DATE NOT NULL ,
    u_payment DOUBLE PRECISION,
    c_salary DOUBLE PRECISION
);

create index club_membership_member on club_membership(id_player);
create index club_membership_club on club_membership(id_club);

create table club_event (
    id SERIAL PRIMARY KEY,
    id_club INTEGER REFERENCES club ON DELETE CASCADE NOT NULL,
    id_collective_event INTEGER REFERENCES collective_event ON DELETE CASCADE NOT NULL ,
    cost DOUBLE PRECISION DEFAULT 0.0,
    profit DOUBLE PRECISION DEFAULT 0.0
);

create index club_event_pk on club_event(id);
create index club_event_club on club_event(id_club);
create index club_event_collective_event on club_event(id_collective_event);