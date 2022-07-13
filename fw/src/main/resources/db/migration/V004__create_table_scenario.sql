CREATE TABLE scenario
(
    id         BIGSERIAL PRIMARY KEY,
    uuid       UUID    NOT NULL UNIQUE,
    name       VARCHAR NOT NULL,
    predicate  VARCHAR NOT NULL,
    action     VARCHAR NOT NULL,
    isDefault  BOOL DEFAULT FALSE,
    service_id BIGINT  NOT NULL
);

ALTER TABLE scenario
    ADD CONSTRAINT service_scenario_fk FOREIGN KEY (service_id) REFERENCES service (id)