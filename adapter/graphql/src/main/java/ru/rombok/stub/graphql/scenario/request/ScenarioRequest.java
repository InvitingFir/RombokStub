package ru.rombok.stub.graphql.scenario.request;

import lombok.Data;
import ru.rombok.stub.graphql.variable.request.ScenarioVariableRequest;

import java.util.List;

@Data
public class ScenarioRequest {
    private String scenarioType;
    private String name;
    private Boolean isDefault;
    private String url;
    private List<ScenarioVariableRequest> variables;
}
