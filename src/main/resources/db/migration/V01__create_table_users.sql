CREATE TABLE IF NOT EXISTS users (
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(30) NOT NULL,
    email             VARCHAR(150) NOT NULL UNIQUE,
    fullname          VARCHAR NOT NULL,
    nickname          VARCHAR(25) NOT NULL UNIQUE,
    password          VARCHAR NOT NULL,
    path_image        VARCHAR NOT NULL,
    birth_date        DATE NOT NULL,
    update_date       DATE,
    registration_date DATE NOT NULL
);