CREATE TABLE users
(
    id       INT PRIMARY KEY,
    email    VARCHAR(255) UNIQUE                                NOT NULL,
    password VARCHAR(20),
    role     VARCHAR(20) CHECK ( role IN ('customer', 'admin')) NOT NULL
);