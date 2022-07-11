CREATE TABLE service
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL,
    enabled BOOL DEFAULT FALSE
)