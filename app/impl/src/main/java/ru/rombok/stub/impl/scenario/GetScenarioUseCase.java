package ru.rombok.stub.impl.scenario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.GetScenarioInbound;
import ru.rombok.stub.api.scenario.exception.ScenarioNotFoundException;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetScenarioUseCase implements GetScenarioInbound {
    private final ScenarioRepository repository;

    @Override
    public Scenario execute(UUID scenarioUuid) {
        return repository
            .get(scenarioUuid)
            .orElseThrow(() -> new ScenarioNotFoundException(scenarioUuid));
    }
}
