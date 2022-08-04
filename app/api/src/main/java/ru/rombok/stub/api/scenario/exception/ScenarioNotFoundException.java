package ru.rombok.stub.api.scenario.exception;

import java.util.UUID;

public class ScenarioNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Scenario with uuid %s not found";

    public ScenarioNotFoundException(UUID uuid) {
        super(String.format(MESSAGE, uuid));
    }
}
