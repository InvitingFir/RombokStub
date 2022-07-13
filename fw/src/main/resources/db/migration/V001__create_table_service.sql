CREATE TABLE service
(
    id      BIGSERIAL PRIMARY KEY,
    uuid    UUID    NOT NULL UNIQUE,
    name    VARCHAR NOT NULL,
    enabled BOOL DEFAULT FALSE
)