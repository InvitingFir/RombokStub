package ru.rombok.stub.graphql.scenario.map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.rombok.stub.domain.scenario.HttpScenario;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.graphql.scenario.request.ScenarioRequest;
import ru.rombok.stub.graphql.scenario.response.ScenarioResponse;
import ru.rombok.stub.graphql.util.AdapterMappingException;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;
import static ru.rombok.stub.graphql.util.FunctionalUtils.first;

@Component
public class ScenarioMapper {
    private final ModelMapper mapper;

    public ScenarioMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);
        mapper.typeMap(HttpScenario.class, ScenarioResponse.class)
                .addMapping(ScenarioTypeMapping::getTypeNameForScenario, ScenarioResponse::setScenarioType);
    }

    public ScenarioResponse toDto(Scenario scenario) {
        try {
            return mapper.map(scenario, ScenarioResponse.class);
        } catch (Exception e) {
            throw AdapterMappingException.builder()
                    .from(Scenario.class)
                    .to(ScenarioResponse.class)
                    .sourceException(e)
                    .forSingleObject();
        }
    }

    public List<ScenarioResponse> toDto(List<Scenario> scenarios) {
        try {
            return scenarios.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw AdapterMappingException.builder()
                    .from(Scenario.class)
                    .to(ScenarioResponse.class)
                    .sourceException(e)
                    .forList();
        }
    }

    public Scenario fromDto(ScenarioRequest request) {
        try {
            Scenario scenario = first(ScenarioRequest::getScenarioType)
                    .andThen(ScenarioTypeMapping::getScenarioForTypeName)
                .apply(request);
            mapper.map(request, scenario);
            return scenario;
        } catch (Exception e) {
            throw AdapterMappingException.builder()
                    .from(Scenario.class)
                    .to(ScenarioResponse.class)
                    .sourceException(e)
                    .forSingleObject();
        }
    }
}
