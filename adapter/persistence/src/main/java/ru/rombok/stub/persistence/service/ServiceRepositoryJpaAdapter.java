package ru.rombok.stub.persistence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rombok.stub.api.service.repository.ServiceRepository;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.service.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ServiceRepositoryJpaAdapter implements ServiceRepository {
    private final ServiceJpaRepository repository;

    @Override
    public Optional<Service<Scenario>> get(UUID serviceUuid) {
        return repository.findByUuid(serviceUuid);
    }

    @Override
    public Optional<List<Scenario>> getAllScenarios(UUID serviceUuid) {
        return get(serviceUuid).map(Service::getScenarios);
    }

    @Override
    public <T extends Scenario> Optional<Service<T>> delete(UUID serviceUuid) {
        return repository.deleteByUuid(serviceUuid);
    }
}
