create sequence hibernate_sequence start 1 increment 1;

CREATE TABLE User (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255)
);

CREATE TABLE Account (
    id BIGINT PRIMARY KEY,
    number VARCHAR(255),
    balance DOUBLE PRECISION,
    productLimit DOUBLE PRECISION,
    serviceLimit DOUBLE PRECISION,
    monthlySpent DOUBLE PRECISION,
    category VARCHAR(255),
    limitSetDate DATE,
    currency VARCHAR(255),
    user_id BIGINT REFERENCES User(id)
);


CREATE TABLE Transaction (
    id BIGINT PRIMARY KEY,
    amount DOUBLE PRECISION,
    fromAccountNumber VARCHAR(255),
    toAccountNumber VARCHAR(255),
    category VARCHAR(255),
    limit_exceeded BOOLEAN,
    createDate TIMESTAMP,
    currency VARCHAR(255)
);