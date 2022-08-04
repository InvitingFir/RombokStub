package ru.rombok.stub.graphql.scenario;

import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.stereotype.Component;
import ru.rombok.stub.domain.scenario.HttpScenario;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.graphql.scenario.request.ScenarioRequest;
import ru.rombok.stub.graphql.scenario.request.ScenarioType;
import ru.rombok.stub.graphql.scenario.response.ScenarioResponse;
import ru.rombok.stub.graphql.util.AdapterMappingException;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;
import static ru.rombok.stub.graphql.util.FunctionalUtils.first;

@Component
public class ScenarioMapper {
    private final Provider<Scenario> forRequest = request -> first(Provider.ProvisionRequest<Scenario>::getSource)
        .andThen(ScenarioRequest.class::cast)
        .andThen(ScenarioRequest::getScenarioType)
        .andThen(ScenarioType::getScenarioForTypeName)
        .apply(request);
    private final ModelMapper mapper;

    public ScenarioMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);
        mapper.typeMap(Scenario.class, ScenarioResponse.class)
            .addMapping(ScenarioType::getTypeNameForScenario, ScenarioResponse::setScenarioType);
        mapper.typeMap(HttpScenario.class, ScenarioResponse.class)
            .addMapping(ScenarioType::getTypeNameForScenario, ScenarioResponse::setScenarioType);
    }

    public ScenarioResponse toDto(Scenario scenario) {
        try {
            return mapper.map(scenario, ScenarioResponse.class);
        } catch (Exception e) {
            throw AdapterMappingException.forObject(Scenario.class, ScenarioResponse.class, e);
        }
    }

    public List<ScenarioResponse> toDto(List<Scenario> scenarios) {
        try {
            return scenarios.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw AdapterMappingException.forListOf(Scenario.class, ScenarioResponse.class, e);
        }
    }

    public Scenario fromDto(ScenarioRequest request) {
        try {
            Scenario scenario = first(ScenarioRequest::getScenarioType)
                .andThen(ScenarioType::getScenarioForTypeName)
                .apply(request);
            mapper.map(request, scenario);
            return scenario;
        } catch (Exception e) {
            throw AdapterMappingException.forObject(Scenario.class, ScenarioResponse.class, e);
        }
    }
}
