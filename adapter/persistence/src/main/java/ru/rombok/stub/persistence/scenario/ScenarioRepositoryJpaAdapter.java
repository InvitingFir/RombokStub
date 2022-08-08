package ru.rombok.stub.persistence.scenario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ScenarioRepositoryJpaAdapter implements ScenarioRepository {
    private final ScenarioJpaRepository repository;

    @Override
    public Optional<Scenario> get(UUID scenarioUuid) {
        return repository.findByUuid(scenarioUuid);
    }

    @Override
    public Optional<List<Scenario>> getAllForService(UUID serviceUuid) {
        return repository.findAllByServiceUuid(serviceUuid);
    }

    @Override
    public Optional<Scenario> delete(UUID scenarioUuid) {
        return repository.deleteByUuid(scenarioUuid);
    }
}
