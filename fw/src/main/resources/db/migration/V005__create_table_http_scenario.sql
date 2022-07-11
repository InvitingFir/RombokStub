CREATE TABLE http_scenario
(
    id  BIGSERIAL PRIMARY KEY,
    url VARCHAR
);

ALTER TABLE http_scenario
    ADD CONSTRAINT scenario_http_scenario_fk FOREIGN KEY (id) REFERENCES scenario (id)