package ru.rombok.stub.api.scenario;

import ru.rombok.stub.domain.scenario.Scenario;

import java.util.UUID;

public interface DeleteScenarioInbound {

    Scenario execute(UUID scenarioUuid);
}
