package ru.rombok.stub.graphql.scenario.response;

import lombok.Data;
import ru.rombok.stub.graphql.variable.response.ScenarioVariableResponse;

import java.util.List;

@Data
public class ScenarioResponse {
    private String scenarioType;
    private String name;
    private String predicate;
    private String action;
    private Boolean isDefault;
    private String url;
    private String uuid;
    private List<ScenarioVariableResponse> variables;
}
