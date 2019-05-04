CREATE SCHEMA IF NOT EXISTS demo;


DROP TABLE IF EXISTS demo.user;

CREATE TABLE demo.user
(
    usr_id           SERIAL PRIMARY KEY,
    usr_first_name   VARCHAR(200) NOT NULL,
    usr_last_name    VARCHAR(200) NOT NULL,
    usr_user_name    VARCHAR(100) NOT NULL UNIQUE,
    usr_email        VARCHAR(500) NOT NULL UNIQUE,
    usr_phone_number VARCHAR(100) NOT NULL,
    usr_age          INTEGER NOT NULL,
    usr_website      VARCHAR(500)
);
