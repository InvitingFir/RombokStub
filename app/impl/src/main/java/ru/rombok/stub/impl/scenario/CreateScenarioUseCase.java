package ru.rombok.stub.impl.scenario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.CreateScenarioInbound;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateScenarioUseCase implements CreateScenarioInbound {
    private final ScenarioRepository repository;

    @Override
    public Scenario execute(Scenario scenario, UUID serviceUuid) {
        scenario.setUuid(UUID.randomUUID());
        return repository.save(scenario);
    }
}
