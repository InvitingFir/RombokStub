CREATE TABLE execution_log
(
    id              BIGSERIAL PRIMARY KEY,
    success         BOOL DEFAULT FALSE,
    error_type      VARCHAR,
    start_date_time DATE    NOT NULL,
    end_date_time   DATE    NOT NULL,
    source          VARCHAR NOT NULL,
    destination     VARCHAR NOT NULL,
    scenario_id BIGINT
);

ALTER TABLE execution_log
    ADD CONSTRAINT scenario_execution_log_fk FOREIGN KEY (scenario_id) REFERENCES scenario (id)