package ru.rombok.stub.graphql.scenario;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import ru.rombok.stub.api.scenario.CreateScenarioInbound;
import ru.rombok.stub.api.scenario.DeleteScenarioInbound;
import ru.rombok.stub.api.scenario.update.UpdateScenarioInbound;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.graphql.scenario.request.ScenarioRequest;
import ru.rombok.stub.graphql.scenario.response.ScenarioResponse;
import ru.rombok.stub.graphql.util.UUIDPattern;

import javax.validation.Valid;
import java.util.UUID;
import java.util.function.Function;

import static ru.rombok.stub.graphql.util.FunctionalUtils.first;

@Component
@Validated
@RequiredArgsConstructor
public class ScenarioMutationResolver implements GraphQLMutationResolver {
    private final CreateScenarioInbound createScenario;
    private final UpdateScenarioInbound updateScenario;
    private final DeleteScenarioInbound deleteScenario;
    private final ScenarioMapper mapper;

    public ScenarioResponse createScenario(@Valid ScenarioRequest params, @UUIDPattern String serviceUuid) {
        Function<UUID, Scenario> createScenarioForService = uuid -> first(mapper::fromDto)
            .andThen(scenario -> createScenario.execute(scenario, uuid))
            .apply(params);

        return first(UUID::fromString)
            .andThen(createScenarioForService)
            .andThen(mapper::toDto)
            .apply(serviceUuid);
    }

    public ScenarioResponse updateScenario(@Valid ScenarioRequest updates, @UUIDPattern String scenarioUuid) {
        Function<UUID, Scenario> updateScenarioWithUuid = uuid -> first(mapper::fromDto)
            .andThen(scenario -> updateScenario.execute(uuid, scenario))
            .apply(updates);

        return first(UUID::fromString)
            .andThen(updateScenarioWithUuid)
            .andThen(mapper::toDto)
            .apply(scenarioUuid);
    }

    public ScenarioResponse deleteScenario(@UUIDPattern String uuid) {
        return first(UUID::fromString)
            .andThen(deleteScenario::execute)
            .andThen(mapper::toDto)
            .apply(uuid);
    }
}
