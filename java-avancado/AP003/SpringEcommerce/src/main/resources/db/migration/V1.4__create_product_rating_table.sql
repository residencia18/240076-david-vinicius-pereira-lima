-- V1.4__create_product_rating_table.sql

CREATE TABLE IF NOT EXISTS public.product_rating (
    id SERIAL PRIMARY KEY,
    rating_stars NUMERIC(1, 0) NOT NULL,
    review VARCHAR(255),
    user_name VARCHAR(16) NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);