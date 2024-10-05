CREATE TABLE orders
(
    id               SERIAL PRIMARY KEY,
    user_id          INT       NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    delivery_address VARCHAR(255),
    contact_phone    VARCHAR(20),
    delivery_method  VARCHAR(100),
    status           VARCHAR(50),
    updated_at       TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
