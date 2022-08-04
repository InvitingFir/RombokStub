package ru.rombok.stub.impl.scenario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.GetAllScenariosInbound;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetAllScenariosUseCase implements GetAllScenariosInbound {
    private final ScenarioRepository repository;

    @Override
    public List<Scenario> execute(UUID serviceUuid) {
        return repository.getAllWithServiceUuid(serviceUuid);
    }
}
