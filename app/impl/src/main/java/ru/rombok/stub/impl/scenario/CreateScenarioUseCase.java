package ru.rombok.stub.impl.scenario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rombok.stub.api.scenario.CreateScenarioInbound;
import ru.rombok.stub.api.service.exception.ServiceNotFoundException;
import ru.rombok.stub.api.service.repository.ServiceRepository;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.service.Service;
import ru.rombok.stub.impl.common.UuidUtils;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateScenarioUseCase implements CreateScenarioInbound {
    private final ServiceRepository serviceRepository;
    private final UuidUtils uuid;

    @Override
    @Transactional
    public Scenario execute(Scenario scenario, UUID serviceUuid) {
        Service<Scenario> service = serviceRepository
            .get(serviceUuid)
            .orElseThrow(() -> new ServiceNotFoundException(serviceUuid));
        scenario.setUuid(uuid.randomUuid());
        service.getScenarios().add(scenario);
        return scenario;
    }
}
