-- V1.2__create_categor_possible_facets_table.sql

CREATE TABLE IF NOT EXISTS public.category_possible_facets (
    id SERIAL PRIMARY KEY,
    category_id INT,
    facet VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL
);