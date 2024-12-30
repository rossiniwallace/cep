CREATE TABLE external_query_log (
    id SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    http_method VARCHAR(10) NOT NULL,
    request_params TEXT NOT NULL,
    response TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);