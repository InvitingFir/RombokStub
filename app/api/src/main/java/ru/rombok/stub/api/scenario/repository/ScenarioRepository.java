package ru.rombok.stub.api.scenario.repository;

import ru.rombok.stub.domain.file.File;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScenarioRepository {

    Optional<Scenario> get(UUID scenarioUuid);

    Optional<List<Scenario>> getAllForService(UUID serviceUuid);

    Optional<Scenario> delete(UUID scenarioUuid);

    Optional<File> getPredicate(UUID scenarioUuid);

    Optional<File> getAction(UUID scenarioUuid);

}
