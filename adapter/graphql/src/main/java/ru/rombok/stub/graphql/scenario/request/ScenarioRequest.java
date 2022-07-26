package ru.rombok.stub.graphql.scenario.request;

import lombok.Data;

@Data
public class ScenarioRequest {
    private String scenarioType;
    private String name;
    private String predicate;
    private String action;
    private Boolean isDefault;
    private String url;
}
