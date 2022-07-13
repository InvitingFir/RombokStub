package ru.rombok.stub.api.service;

import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.service.Service;

import java.util.UUID;

public interface DeleteServiceInbound {

    <T extends Scenario> Service<T> execute(UUID serviceUuid);
}
