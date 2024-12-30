CREATE TABLE deliveries (
    id SERIAL PRIMARY KEY,
    origin VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    order_date TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    tracking_code VARCHAR(255) UNIQUE NOT NULL
);