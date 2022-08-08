package ru.rombok.stub.graphql.scenario.request;

import lombok.AccessLevel;
import lombok.Getter;
import ru.rombok.stub.domain.scenario.HttpScenario;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.Arrays;
import java.util.function.Supplier;

@Getter(AccessLevel.PRIVATE)
public enum ScenarioType {
    HTTP("HTTP", HttpScenario::new);

    private final String name;
    private final Supplier<Scenario> scenarioProvider;

    ScenarioType(String name, Supplier<Scenario> supplier) {
        this.name = name;
        this.scenarioProvider = supplier;
    }

    public static Scenario getScenarioForTypeName(String name) {
        return Arrays.stream(ScenarioType.values())
            .filter(type -> type.name.equals(name))
            .map(ScenarioType::getScenarioProvider)
            .map(Supplier::get)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(String.format("Scenario with type %s is not supported", name)));
    }

    public static <T extends Scenario> String getTypeNameForScenario(T scenario) {
        if (scenario instanceof HttpScenario) {
            return HTTP.getName();
        } else {
            throw new IllegalArgumentException("Can't get type name for " + scenario.getClass());
        }
    }
}
