package ru.rombok.stub.domain.scenario;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import ru.rombok.stub.domain.DomainObject;
import ru.rombok.stub.domain.file.File;
import ru.rombok.stub.domain.service.Service;
import ru.rombok.stub.domain.var.ScenarioVariable;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Stubbed service scenario
 */
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue("Default")
@JsonTypeInfo(property = "type", use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
    @JsonSubTypes.Type(value = HttpScenario.class, name = "HTTP"),
    @JsonSubTypes.Type(value = Scenario.class, name = "Default")
})

@NamedEntityGraph(name = "Scenario.withVars",
    attributeNodes = {
        @NamedAttributeNode("variables")
    }
)
public class Scenario extends DomainObject {
    /**
     * Scenario displayed name
     */
    private String name;

    /**
     * Unique identifier
     */
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID uuid;

    /**
     * Predicate for scenario activation
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "predicate_id")
    private File predicate;

    /**
     * Stubbing action
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private File action;

    /**
     * If there are no scenarios for this service
     * and 'isDefault' is true, then this scenario
     * will be activated. Only one scenario can be
     * set to default
     */
    private Boolean isDefault;

    /**
     * Scenario variables
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
        name = "scenario_id",
        foreignKey = @ForeignKey(name = "scenario_scenario_variable_fk"))
    private List<ScenarioVariable> variables;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service<Scenario> service;

    public void setVariables(List<ScenarioVariable> variables) {
        if (this.variables == null || variables == null) {
            this.variables = variables;
        } else {
            this.variables.clear();
            this.variables.addAll(variables);
        }
    }
}
