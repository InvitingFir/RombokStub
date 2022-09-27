package ru.rombok.stub.persistence.logs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.logs.repository.ExecutionLogRepository;
import ru.rombok.stub.domain.log.ExecutionLog;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ExecutionLogRepositoryJpaAdapter implements ExecutionLogRepository {
    private final ExecutionLogJpaRepository repository;

    @Override
    public List<ExecutionLog> findAllForScenario(UUID scenarioUuid) {
        return repository.findAllWithScenarioUuid(scenarioUuid);
    }
}
