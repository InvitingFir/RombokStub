package ru.rombok.stub.domain.service;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.scenario.HttpScenario;

/**
 * Service for stubbing http responses
 */
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("HTTP")
public class HttpService extends Service<HttpScenario> {

}
