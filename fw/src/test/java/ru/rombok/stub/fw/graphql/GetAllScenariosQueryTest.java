package ru.rombok.stub.fw.graphql;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rombok.stub.api.service.repository.ServiceRepository;
import ru.rombok.stub.domain.scenario.HttpScenario;
import ru.rombok.stub.domain.service.HttpService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

class GetAllScenariosQueryTest extends AbstractGraphQLTest {

    @MockBean
    ServiceRepository repository;

    @Override
    public String getTestSourceName() {
        return "GetAllScenarios";
    }

    @Test
    void getAllScenarios() throws Exception {
        HttpService service = provider.forClass(HttpService.class);
        service.setScenarios(List.of(provider.forClass(HttpScenario.class), provider.forClass(HttpScenario.class)));
        doReturn(Optional.of(service)).when(repository).get(any());
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
        doReturn(Optional.empty()).when(repository).get(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("getAllScenarios.json"), ObjectNode.class);

        GraphQLResponse getAllScenarios = testTemplate.perform(getRequestSourcePath("getAllScenarios.graphql"), "getAllScenarios", variables);

        getAllScenarios
            .assertThatErrorsField().isNotPresent()
            .and()
            .assertThatJsonContent().isEqualToJson(getExpectedSourcePath("getAllScenarios_noService.json"), this.getClass());
    }
}
