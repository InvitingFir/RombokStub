package ru.rombok.stub.domain.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.scenario.Scenario;

import javax.persistence.Entity;

/**
 * Service for stubbing Kafka responses
 */
@Getter
@Setter
@ToString
@Entity
public class KafkaService extends Service<Scenario> {

    /**
     * Input topic name
     */
    private String inputTopic;

    /**
     * Output topic name
     */
    private String outputTopic;

    /**
     * Connection address to broker
     */
    private String brokerAddress;
}
