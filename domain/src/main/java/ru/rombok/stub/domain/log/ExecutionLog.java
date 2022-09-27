package ru.rombok.stub.domain.log;

import lombok.*;
import ru.rombok.stub.domain.DomainObject;
import ru.rombok.stub.domain.scenario.Scenario;

import javax.persistence.*;
import java.time.Instant;

/**
 * Single scenario activation log
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(
        foreignKey = @ForeignKey(name = "scenario_execution_log_fk"),
        name = "scenario_id")
    private Scenario scenario;
}
