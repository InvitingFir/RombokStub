package ru.rombok.stub.api.scenario.update;

import java.util.UUID;

/**
 * Error while merging scenario updates
 */
public class ScenarioMergingException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Failed to perform merging for scenario with uuid = %s. %s";

    private ScenarioMergingException(UUID scenarioUuid, String additionalInfo, Exception e) {
        super(String.format(DEFAULT_MESSAGE, scenarioUuid, additionalInfo), e);
    }

    // ===================================================================================================================
    // = Factory methods
    // ===================================================================================================================

    public static ScenarioMergingException getFieldValue(UUID scenarioUuid, Exception e) {
        return new ScenarioMergingException(scenarioUuid, "Failed to get field values", e);
    }
}
