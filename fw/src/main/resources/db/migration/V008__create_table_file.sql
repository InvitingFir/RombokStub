CREATE TABLE file
(
    id                  BIGSERIAL PRIMARY KEY,
    uuid                UUID NOT NULL UNIQUE,
    file_storage_type   VARCHAR NOT NULL,
    content             BYTEA NOT NULL,
    uploaded_at         DATE
);