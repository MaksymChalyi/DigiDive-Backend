CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(20)         NOT NULL,
    role_id  INT                 REFERENCES roles (id) ON DELETE SET NULL
);
