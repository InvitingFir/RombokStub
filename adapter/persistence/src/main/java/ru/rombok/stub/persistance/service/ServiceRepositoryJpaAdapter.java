package ru.rombok.stub.persistance.service;

import org.springframework.stereotype.Repository;
import ru.rombok.stub.api.service.repository.ServiceRepository;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.service.Service;

import java.util.UUID;

@Repository
public class ServiceRepositoryJpaAdapter implements ServiceRepository {

    @Override
    public <T extends Scenario> Service<T> get(UUID serviceUuid) {
        return null;
    }

    @Override
    public <T extends Scenario> Service<T> delete(UUID serviceUuid) {
        return null;
    }
}
