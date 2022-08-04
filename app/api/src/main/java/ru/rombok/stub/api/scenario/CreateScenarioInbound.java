package ru.rombok.stub.api.scenario;

import ru.rombok.stub.domain.scenario.Scenario;

import java.util.UUID;

public interface CreateScenarioInbound {

    Scenario execute(Scenario scenario, UUID serviceUuid);
}
