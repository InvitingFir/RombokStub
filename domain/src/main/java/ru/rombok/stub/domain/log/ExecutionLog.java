package ru.rombok.stub.domain.log;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.DomainObject;

import java.time.Instant;

/**
 * Single scenario activation log
 */
@Getter
@Setter
@ToString
@Entity
public class ExecutionLog extends DomainObject {
    /**
     * Is scenario executed successfully
     */
    private Boolean success;

    /**
     * If execution is failed, this field
     * stores error code. Useful for debugging
     */
    @Enumerated(EnumType.STRING)
    private ErrorType errorType;

    /**
     * Scenario execution start date & time
     */
    private Instant startDateTime;

    /**
     * Scenario execution end date & time
     */
    private Instant endDateTime;

    /**
     * Requesting system (HTTP url, kafka topic, etc.)
     */
    private String source;

    /**
     * Responding system (HTTP url, kafka topic, etc.)
     */
    private String destination;
}
