package ru.rombok.stub.domain.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import ru.rombok.stub.domain.DomainObject;
import ru.rombok.stub.domain.scenario.Scenario;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Stubbed service
 *
 * @param <T> scenario type
 */
@Getter
@Setter
@ToString
@Entity
@JsonTypeInfo(property = "type", use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
    @JsonSubTypes.Type(value = HttpService.class, name = "HTTP"),
    @JsonSubTypes.Type(value = KafkaService.class, name = "Kafka")
})

@NamedEntityGraph(name = "service.with-scenario",
    attributeNodes = @NamedAttributeNode(value = "scenarios")
)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Service<T extends Scenario> extends DomainObject {

    /**
     * Service displayed name
     */
    private String name;

    /**
     * Unique identifier
     */
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID uuid;

    /**
     * Is stub service enabled (disabled by default)
     */
    private boolean enabled;

    /**
     * Service scenarios
     */
    @OneToMany(targetEntity = Scenario.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "service_id",
            foreignKey = @ForeignKey(name = "service_scenario_fk"))
    private List<T> scenarios;

    public void setScenarios(List<T> scenarios) {
        if (this.scenarios == null || scenarios == null) {
            this.scenarios = scenarios;
        } else {
            this.scenarios.clear();
            this.scenarios.addAll(scenarios);
        }
    }
}
