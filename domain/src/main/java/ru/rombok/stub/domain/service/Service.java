package ru.rombok.stub.domain.service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.DomainObject;
import ru.rombok.stub.domain.scenario.Scenario;

import java.util.List;

/**
 * Stubbed service
 *
 * @param <T> scenario type
 */
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Service<T extends Scenario> extends DomainObject {

    /**
     * Service displayed name
     */
    private String name;

    /**
     * Is stub service enabled (disabled by default)
     */
    private boolean enabled;

    /**
     * Service scenarios
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(foreignKey = @ForeignKey(name = "service_scenario_fk"))
    private List<T> scenarios;
}
