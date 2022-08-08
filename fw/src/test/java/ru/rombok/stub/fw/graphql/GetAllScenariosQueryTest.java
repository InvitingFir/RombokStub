package ru.rombok.stub.fw.graphql;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.HttpScenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

class GetAllScenariosQueryTest extends AbstractGraphQLTest {

    @MockBean
    ScenarioRepository repository;

    @Override
    public String getTestSourceName() {
        return "GetAllScenarios";
    }

    @Test
    void getAllScenarios() throws Exception {
        doReturn(Optional.of(new ArrayList<>(List.of(provider.forClass(HttpScenario.class), provider.forClass(HttpScenario.class)))))
            .when(repository).getAllForService(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("getAllScenarios.json"), ObjectNode.class);

        GraphQLResponse getAllScenarios = testTemplate.perform(getRequestSourcePath("getAllScenarios.graphql"), "getAllScenarios", variables);

        getAllScenarios
            .assertThatErrorsField().isNotPresent()
            .and()
            .assertThatJsonContent().isEqualToJson(getExpectedSourcePath("getAllScenarios.json"), this.getClass());
    }

    @Test
    void getAllScenarios_uuidValidationError() throws Exception {
        ObjectNode variables = provider.fromFile(getVariableSourcePath("getAllScenarios_uuidValidationError.json"), ObjectNode.class);

        GraphQLResponse getAllScenarios = testTemplate.perform(getRequestSourcePath("getAllScenarios.graphql"), "getAllScenarios", variables);

        getAllScenarios
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: getAllScenarios.serviceUuid: must have UUID format]");
    }

    @Test
    void getAllScenarios_noService() throws Exception {
        doReturn(Optional.empty()).when(repository).getAllForService(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("getAllScenarios.json"), ObjectNode.class);

        GraphQLResponse getAllScenarios = testTemplate.perform(getRequestSourcePath("getAllScenarios.graphql"), "getAllScenarios", variables);

        getAllScenarios
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: Service with uuid 9161f68d-da9e-4fa2-b0ad-142bce3b4550 not found]");
    }
}
