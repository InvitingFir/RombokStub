package ru.rombok.stub.persistence.scenario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.file.File;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.persistence.scenario.projection.ActionOnly;
import ru.rombok.stub.persistence.scenario.projection.PredicateOnly;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ScenarioRepositoryJpaAdapter implements ScenarioRepository {
    private final ScenarioJpaRepository repository;
    private final ScenarioPredicateJpaRepository predicateJpaRepository;
    private final ScenarioActionJpaRepository actionJpaRepository;

    @Override
    public Optional<Scenario> get(UUID scenarioUuid) {
        return repository.findByUuid(scenarioUuid);
    }

    @Override
    public List<Scenario> getAllForService(UUID serviceUuid) {
        return repository.findAllByServiceUuid(serviceUuid);
    }

    @Override
    public Optional<Scenario> delete(UUID scenarioUuid) {
        return repository.deleteByUuid(scenarioUuid);
    }

    @Override
    public Optional<File> getPredicate(UUID scenarioUuid) {
        return predicateJpaRepository.findByUuid(scenarioUuid).map(PredicateOnly::getPredicate);
    }

    @Override
    public Optional<File> getAction(UUID scenarioUuid) {
        return actionJpaRepository.findByUuid(scenarioUuid).map(ActionOnly::getAction);
    }
}
