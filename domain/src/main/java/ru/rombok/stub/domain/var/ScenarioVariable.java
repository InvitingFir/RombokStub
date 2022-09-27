package ru.rombok.stub.domain.var;

import lombok.*;
import ru.rombok.stub.domain.DomainObject;

import javax.persistence.Entity;

/**
 * Constant variables, that can be used in scenario
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
