package ru.rombok.stub.domain.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.rombok.stub.domain.scenario.HttpScenario;

import javax.persistence.Entity;

/**
 * Service for stubbing http responses
 */
@Getter
@Setter
@ToString
@Entity
public class HttpService extends Service<HttpScenario> {

}
