-- V1.5__create_product_attribute_table.sql

CREATE TABLE IF NOT EXISTS public.product_attribute (
    id SERIAL PRIMARY KEY,
    attribute_name VARCHAR(255) NOT NULL,
    attribute_value VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);