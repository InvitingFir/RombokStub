CREATE TABLE scenario
(
    id         BIGSERIAL PRIMARY KEY,
    type       VARCHAR NOT NULL,
    uuid       UUID    NOT NULL UNIQUE,
    name       VARCHAR NOT NULL,
    predicate  VARCHAR NOT NULL,
    action     VARCHAR NOT NULL,
    is_default BOOL DEFAULT FALSE,
    service_id BIGINT,
    url        VARCHAR
);

ALTER TABLE scenario
    ADD CONSTRAINT service_scenario_fk FOREIGN KEY (service_id) REFERENCES service (id)