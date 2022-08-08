package ru.rombok.stub.persistence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rombok.stub.api.service.repository.ServiceRepository;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.service.Service;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ServiceRepositoryJpaAdapter implements ServiceRepository {
    private final ServiceJpaRepository repository;

    @Override
    public <T extends Scenario> Optional<Service<T>> get(UUID serviceUuid) {
        return repository.findByUuid(serviceUuid);
    }

    @Override
    public <T extends Scenario> Optional<Service<T>> delete(UUID serviceUuid) {
        return repository.deleteByUuid(serviceUuid);
    }
}
