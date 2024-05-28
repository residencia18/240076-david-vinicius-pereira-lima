-- V1.7__create_shopping_cart_item_table.sql

CREATE TABLE IF NOT EXISTS public.shopping_cart_item (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    shopping_cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);