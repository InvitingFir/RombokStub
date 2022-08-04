package ru.rombok.stub.persistance.scenario;

import org.springframework.stereotype.Repository;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ScenarioRepositoryJpaAdapter implements ScenarioRepository {

    @Override
    public Scenario save(Scenario scenario) {
        return null;
    }

    @Override
    public Optional<Scenario> get(UUID scenarioUuid) {
        return Optional.empty();
    }

    @Override
    public Optional<Scenario> delete(UUID scenarioUuid) {
        return Optional.empty();
    }

    @Override
    public List<Scenario> getAllWithServiceUuid(UUID serviceUuid) {
        return null;
    }
}
