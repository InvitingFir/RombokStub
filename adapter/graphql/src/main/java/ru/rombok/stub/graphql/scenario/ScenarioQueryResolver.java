package ru.rombok.stub.graphql.scenario;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import ru.rombok.stub.api.scenario.GetAllScenariosInbound;
import ru.rombok.stub.api.scenario.GetScenarioInbound;
import ru.rombok.stub.graphql.scenario.response.ScenarioResponse;
import ru.rombok.stub.graphql.util.UUIDPattern;

import java.util.List;
import java.util.UUID;

import static ru.rombok.stub.graphql.util.FunctionalUtils.first;

@Component
@Validated
@RequiredArgsConstructor
public class ScenarioQueryResolver implements GraphQLQueryResolver {
    private final GetScenarioInbound getScenario;
    private final GetAllScenariosInbound getAllScenarios;
    private final ScenarioMapper mapper;

    public ScenarioResponse getScenario(@UUIDPattern String scenarioUuid) {
        return first(UUID::fromString)
            .andThen(getScenario::execute)
            .andThen(mapper::toDto)
            .apply(scenarioUuid);
    }

    public List<ScenarioResponse> getAllScenarios(@UUIDPattern String serviceUuid) {
        return first(UUID::fromString)
            .andThen(getAllScenarios::execute)
            .andThen(mapper::toDto)
            .apply(serviceUuid);
    }
}
