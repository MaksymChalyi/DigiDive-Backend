CREATE TABLE product_images
(
    id         SERIAL PRIMARY KEY,
    product_id INT          NOT NULL,
    image_url  VARCHAR(255) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);
