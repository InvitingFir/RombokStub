package ru.rombok.stub.api.service.repository;

import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.service.Service;

import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository {

    <T extends Scenario> Optional<Service<T>> get(UUID serviceUuid);

    <T extends Scenario> Optional<Service<T>> delete(UUID serviceUuid);
}
