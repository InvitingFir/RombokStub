package ru.rombok.stub.api.scenario;

import ru.rombok.stub.domain.scenario.Scenario;

import java.util.List;
import java.util.UUID;

public interface GetAllScenariosInbound {

    List<Scenario> execute(UUID serviceUuid);
}
