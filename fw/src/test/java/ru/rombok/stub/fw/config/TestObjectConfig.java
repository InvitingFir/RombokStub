package ru.rombok.stub.fw.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import ru.rombok.stub.domain.log.ExecutionLog;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.var.ScenarioVariable;
import ru.rombok.stub.test.TestObjectProvider;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration
@RequiredArgsConstructor
public class TestObjectConfig {
    private final TestObjectProvider provider;

    @Bean
    @Scope("prototype")
    @DependsOn({"scenarioVariable, executionLog"})
    public Scenario scenario(ScenarioVariable variable, ExecutionLog log) {
        Scenario scenario = provider.fromFile("/testObject/Scenario.json", Scenario.class);
        scenario.setVariables(new ArrayList<>(List.of(variable)));
        scenario.setLogs(new ArrayList<>(List.of(log)));
        return scenario;
    }

    @Bean
    @Scope("prototype")
    public ScenarioVariable scenarioVariable() {
        return provider.fromFile("/testObject/ScenarioVariable.json", ScenarioVariable.class);
    }

    @Bean
    @Scope("prototype")
    public ExecutionLog executionLog() {
        return provider.fromFile("/testObject/ExecutionLog.json", ExecutionLog.class);
    }
}
