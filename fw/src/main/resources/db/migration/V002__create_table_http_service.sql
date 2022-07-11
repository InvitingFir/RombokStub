CREATE TABLE http_service
(
    id BIGSERIAL PRIMARY KEY
);

ALTER TABLE http_service
    ADD CONSTRAINT service_http_service FOREIGN KEY (id) references service (id)