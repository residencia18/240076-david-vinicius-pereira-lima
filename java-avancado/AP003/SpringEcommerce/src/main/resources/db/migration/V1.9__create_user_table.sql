-- V1.9__create_user_table.sql

CREATE TABLE IF NOT EXISTS public.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(16) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    is_enabled BOOLEAN DEFAULT false,
    role VARCHAR(50) NOT NULL,
    otp VARCHAR(255),
    otp_generation_time TIMESTAMP WITHOUT TIME ZONE
);