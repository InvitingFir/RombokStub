package ru.rombok.stub.persistence.service;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.service.Service;

import java.util.Optional;
import java.util.UUID;

public interface ServiceJpaRepository<T extends Service<? extends Scenario>> extends CrudRepository<T, Long> {

    @EntityGraph("service.with-scenario")
    <T extends Scenario> Optional<Service<T>> findByUuid(UUID uuid);

    <T extends Scenario> Optional<Service<T>> deleteByUuid(UUID uuid);
}
