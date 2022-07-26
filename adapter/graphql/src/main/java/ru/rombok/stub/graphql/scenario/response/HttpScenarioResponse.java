package ru.rombok.stub.graphql.scenario.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HttpScenarioResponse extends ScenarioResponse {
    private String url;
}
