-- V1.6__create_shopping_cart_table.sql

CREATE TABLE IF NOT EXISTS public.shopping_cart (
    id SERIAL PRIMARY KEY,
    cart_total_price NUMERIC(10, 2) NOT NULL,
    number_of_items INT DEFAULT 0,
    username VARCHAR(30),
    CONSTRAINT shopping_cart_username_unique UNIQUE (username)
);