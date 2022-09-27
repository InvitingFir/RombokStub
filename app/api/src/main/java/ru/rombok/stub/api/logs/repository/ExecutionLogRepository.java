package ru.rombok.stub.api.logs.repository;

import ru.rombok.stub.domain.log.ExecutionLog;

import java.util.List;
import java.util.UUID;

public interface ExecutionLogRepository {

    List<ExecutionLog> findAllForScenario(UUID scenarioUuid);
}
