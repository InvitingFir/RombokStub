package ru.rombok.stub.persistence.logs;

import org.springframework.data.repository.CrudRepository;
import ru.rombok.stub.domain.log.ExecutionLog;

import java.util.List;
import java.util.UUID;

public interface ExecutionLogJpaRepository extends CrudRepository<ExecutionLog, Long> {

    List<ExecutionLog> findAllWithScenarioUuid(UUID scenarioUuid);
}
