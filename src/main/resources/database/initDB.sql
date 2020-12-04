CREATE TABLE IF NOT EXISTS currencies
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(20) NOT NULL ,
    coefficient_integer_part SERIAL NOT NULL ,
    coefficient_float_part SERIAL NOT NULL
);