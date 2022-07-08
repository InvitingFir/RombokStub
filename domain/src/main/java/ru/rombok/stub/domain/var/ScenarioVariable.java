package ru.rombok.stub.domain.var;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.DomainObject;

/**
 * Constant variables, that can be used in scenario
 */
@Getter
@Setter
@ToString
@Entity
public class ScenarioVariable extends DomainObject {
    /**
     * Variable name
     */
    private String name;

    /**
     * Variable string value (JSON, XML, etc.)
     */
    private String value;
}
