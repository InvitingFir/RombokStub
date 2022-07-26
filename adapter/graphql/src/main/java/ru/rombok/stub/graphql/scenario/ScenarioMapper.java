package ru.rombok.stub.graphql.scenario;

import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import ru.rombok.stub.domain.scenario.HttpScenario;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.graphql.scenario.request.ScenarioRequest;
import ru.rombok.stub.graphql.scenario.request.ScenarioType;
import ru.rombok.stub.graphql.scenario.response.ScenarioResponse;
import ru.rombok.stub.graphql.util.AdapterMappingException;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

public class ScenarioMapper {
    private final Provider<Scenario> forRequest = ctx ->
        ScenarioType.getScenarioForTypeName(((ScenarioRequest) ctx.getSource()).getScenarioType());
    private final ModelMapper mapper;

    public ScenarioMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);
        mapper.typeMap(ScenarioRequest.class, Scenario.class)
            .setProvider(forRequest);
        mapper.typeMap(ScenarioRequest.class, HttpScenario.class)
            .includeBase(ScenarioRequest.class, Scenario.class);
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
            return mapper.map(request, Scenario.class);
        } catch (Exception e) {
            throw AdapterMappingException.forObject(Scenario.class, ScenarioResponse.class, e);
        }
    }
}
