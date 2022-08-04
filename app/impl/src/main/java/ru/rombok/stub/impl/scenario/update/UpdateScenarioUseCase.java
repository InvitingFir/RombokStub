package ru.rombok.stub.impl.scenario.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rombok.stub.api.scenario.exception.ScenarioNotFoundException;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.api.scenario.update.UpdateScenarioInbound;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateScenarioUseCase implements UpdateScenarioInbound {
    private final ScenarioRepository repository;
    private final ScenarioMerger merger;

    @Override
    @Transactional
    public Scenario execute(UUID scenarioUuid, Scenario updatedScenario) {
        Scenario originalScenario = repository.get(scenarioUuid).orElseThrow(() -> new ScenarioNotFoundException(scenarioUuid));
        return merger.merge(originalScenario, updatedScenario);
    }
}
