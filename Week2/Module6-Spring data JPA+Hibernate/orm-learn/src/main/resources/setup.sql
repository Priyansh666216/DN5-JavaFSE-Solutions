-- =====================================================
-- Run these commands in MySQL Workbench or MySQL CLI
-- before starting the Spring Boot application
-- =====================================================

-- Step 1: Create the schema
CREATE SCHEMA IF NOT EXISTS ormlearn;

-- Step 2: Switch to the schema
USE ormlearn;

-- Step 3: Create the country table
-- co_code — 2-char primary key (e.g. 'IN', 'US')
-- co_name — full country name
CREATE TABLE IF NOT EXISTS country (
    co_code VARCHAR(2)  PRIMARY KEY,
    co_name VARCHAR(50) NOT NULL
);

-- Step 4: Insert sample data
INSERT INTO country VALUES ('IN', 'India');
INSERT INTO country VALUES ('US', 'United States of America');
INSERT INTO country VALUES ('UK', 'United Kingdom');
INSERT INTO country VALUES ('AU', 'Australia');

-- Step 5: Verify the data
SELECT * FROM country;
