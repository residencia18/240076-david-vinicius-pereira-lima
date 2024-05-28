-- V1.3__create_products_table.sql

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    price NUMERIC(10, 2) NOT NULL,
    sku VARCHAR(50) NOT NULL,
    imageUrl VARCHAR(1000),
    category_id BIGINT NOT NULL,
    quantity INTEGER,
    manufacturer VARCHAR(100),
    featured BOOLEAN
);
