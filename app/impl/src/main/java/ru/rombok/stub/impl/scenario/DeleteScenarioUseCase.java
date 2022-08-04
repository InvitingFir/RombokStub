package ru.rombok.stub.impl.scenario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.DeleteScenarioInbound;
import ru.rombok.stub.api.scenario.exception.ScenarioNotFoundException;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteScenarioUseCase implements DeleteScenarioInbound {
    private final ScenarioRepository repository;

    @Override
    public Scenario execute(UUID scenarioUuid) {
        return repository.delete(scenarioUuid).orElseThrow(() -> new ScenarioNotFoundException(scenarioUuid));
    }
}
