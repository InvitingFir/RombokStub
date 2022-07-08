package ru.rombok.stub.domain.scenario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.DomainObject;
import ru.rombok.stub.domain.log.ExecutionLog;
import ru.rombok.stub.domain.var.ScenarioVariable;

import java.util.List;

/**
 * Stubbed service scenario
 */
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Scenario extends DomainObject {
    /**
     * Scenario displayed name
     */
    private String name;

    /**
     * Predicate for scenario activation
     */
    private String predicate;

    /**
     * Stubbing action
     */
    private String action;

    /**
     * If there are no scenarios for this service
     * and 'isDefault' is true, then this scenario
     * will be activated. Only one scenario can be
     * set to default
     */
    private boolean isDefault;

    /**
     * Scenario variables
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(foreignKey = @ForeignKey(name = "scenario_scenario_variable_fk"))
    private List<ScenarioVariable> variables;

    /**
     * Scenario execution history logs
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(foreignKey = @ForeignKey(name = "scenario_execution_log_fk"))
    private List<ExecutionLog> logs;
}
