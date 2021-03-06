package ru.rombok.stub.domain.scenario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Scenario for http service
 */
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("HTTP")
public class HttpScenario extends Scenario {
    /**
     * Activation URL
     */
    private String url;
}
