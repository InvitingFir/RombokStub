package ru.rombok.stub.api.service;

import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.service.Service;

public interface CreateServiceInbound {

    <T extends Scenario> Service<T> execute(Service<T> service);
}
