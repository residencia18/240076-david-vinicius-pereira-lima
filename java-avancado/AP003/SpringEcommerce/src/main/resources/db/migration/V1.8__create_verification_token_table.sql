-- V1.8__create_verification_token_table.sql

CREATE TABLE IF NOT EXISTS public.token (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) UNIQUE NOT NULL,
    user_id BIGINT NOT NULL,
    expiry_date TIMESTAMP WITHOUT TIME ZONE NOT NULL
);