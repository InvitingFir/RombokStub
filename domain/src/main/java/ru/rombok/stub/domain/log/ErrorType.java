package ru.rombok.stub.domain.log;

import lombok.RequiredArgsConstructor;

/**
 * Scenario execution error type
 */
@RequiredArgsConstructor
public enum ErrorType {

    ACTION_COMPILATION_ERROR("Error occurred while action compilation"),

    PREDICATE_COMPILATION_ERROR("Error occurred while predicate compilation"),

    NO_SUITABLE_SCENARIO("Service doesn't provide scenario, that suites current request params"),

    RESPONSE_SENDING_ERROR("Error occurred while sending response to destination");

    /**
     * Error description
     */
    private final String description;
}
