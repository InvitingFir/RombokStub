package ru.rombok.stub.persistence.scenario;

import org.springframework.data.repository.CrudRepository;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.persistence.scenario.projection.PredicateOnly;

import java.util.Optional;
import java.util.UUID;

public interface ScenarioPredicateJpaRepository extends CrudRepository<Scenario, Long> {

    Optional<PredicateOnly> findByUuid(UUID uuid);
}
