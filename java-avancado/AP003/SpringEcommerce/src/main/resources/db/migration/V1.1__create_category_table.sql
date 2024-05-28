-- V1.1__create_category_table.sql

CREATE TABLE IF NOT EXISTS public.category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);