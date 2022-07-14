package ru.rombok.stub.api.scenario.update;

import ru.rombok.stub.domain.scenario.Scenario;

import java.util.UUID;

public interface UpdateScenarioInbound {

    Scenario execute(UUID scenarioUuid, Scenario scenario);
}
