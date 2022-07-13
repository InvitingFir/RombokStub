package ru.rombok.stub.api.scenario;

import ru.rombok.stub.domain.scenario.Scenario;

public interface CreateScenarioInbound {

    Scenario execute(Scenario scenario);
}
