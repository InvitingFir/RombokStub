package ru.rombok.stub.persistence.scenario;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScenarioJpaRepository extends CrudRepository<Scenario, Long> {

    @EntityGraph("Scenario.withVars")
    Optional<Scenario> findByUuid(UUID uuid);

    @EntityGraph("Scenario.withVars")
    Optional<List<Scenario>> findAllByServiceUuid(UUID serviceUuid);

    Optional<Scenario> deleteByUuid(UUID uuid);
}
