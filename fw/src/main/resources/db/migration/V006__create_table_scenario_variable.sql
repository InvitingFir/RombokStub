CREATE TABLE scenario_variable
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    value       VARCHAR NOT NULL,
    scenario_id BIGINT
);

ALTER TABLE scenario_variable
    ADD CONSTRAINT scenario_scenario_variable_fk FOREIGN KEY (scenario_id) REFERENCES scenario (id)