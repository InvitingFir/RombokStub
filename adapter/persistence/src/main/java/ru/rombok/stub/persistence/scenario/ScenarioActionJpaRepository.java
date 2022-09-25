package ru.rombok.stub.persistence.scenario;

import org.springframework.data.repository.CrudRepository;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.persistence.scenario.projection.ActionOnly;

import java.util.Optional;
import java.util.UUID;

public interface ScenarioActionJpaRepository extends CrudRepository<Scenario, Long> {

    Optional<ActionOnly> findByUuid(UUID uuid);
}
