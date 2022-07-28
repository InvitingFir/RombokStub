package ru.rombok.stub.api.scenario.repository;

import ru.rombok.stub.domain.scenario.Scenario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScenarioRepository {

    Scenario save(Scenario scenario);

    Optional<Scenario> get(UUID scenarioUuid);

    Scenario delete(UUID scenarioUuid);

    List<Scenario> getAllWithServiceUuid(UUID serviceUuid);
}
