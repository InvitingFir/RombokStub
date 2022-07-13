package ru.rombok.stub.api.scenario.repository;

import ru.rombok.stub.domain.scenario.Scenario;

import java.util.UUID;

public interface ScenarioRepository {

    Scenario get(UUID scenarioUuid);

    Scenario delete(UUID scenarioUuid);
}
