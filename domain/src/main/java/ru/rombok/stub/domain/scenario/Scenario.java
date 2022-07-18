package ru.rombok.stub.domain.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.DomainObject;
import ru.rombok.stub.domain.log.ExecutionLog;
import ru.rombok.stub.domain.var.ScenarioVariable;

import java.util.List;
import java.util.UUID;

/**
 * Stubbed service scenario
 */
@Getter
@Setter
@ToString
@Entity
@JsonTypeInfo(property = "type", use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
    @JsonSubTypes.Type(value = HttpScenario.class, name = "HTTP"),
    @JsonSubTypes.Type(value = Scenario.class, name = "Default")
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Scenario extends DomainObject {
    /**
     * Scenario displayed name
     */
    private String name;

    /**
     * Unique identifier
     */
    private UUID uuid;

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
    @JsonProperty(value = "isDefault")
    private Boolean isDefault;

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

    public void setVariables(List<ScenarioVariable> variables) {
        if (this.variables == null || variables == null) {
            this.variables = variables;
        } else {
            this.variables.clear();
            this.variables.addAll(variables);
        }
    }

    public void setLogs(List<ExecutionLog> logs) {
        if (this.logs == null || logs == null) {
            this.logs = logs;
        } else {
            this.logs.clear();
            this.logs.addAll(logs);
        }
    }
}
