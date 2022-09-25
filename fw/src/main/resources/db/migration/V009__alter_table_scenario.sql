ALTER TABLE scenario
    DROP COLUMN action;
ALTER TABLE scenario
    DROP COLUMN predicate;

ALTER TABLE scenario
    ADD COLUMN action_id UUID;

ALTER TABLE scenario
    ADD COLUMN predicate_id UUID;

ALTER TABLE scenario
    ADD CONSTRAINT scenario_action_file_fk FOREIGN KEY (action_id) REFERENCES file(uuid);;
ALTER TABLE scenario
    ADD CONSTRAINT scenario_predicate_file_fk FOREIGN KEY (predicate_id) REFERENCES file(uuid);