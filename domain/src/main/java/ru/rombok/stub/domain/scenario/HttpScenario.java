package ru.rombok.stub.domain.scenario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
