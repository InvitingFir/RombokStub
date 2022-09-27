package ru.rombok.stub.fw.graphql;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.HttpScenario;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

class GetScenarioQueryTest extends AbstractGraphQLTest {

    @MockBean
    ScenarioRepository repository;

    @Override
    public String getTestSourceName() {
        return "GetScenario";
    }

    @Test
    void getScenario_httpScenario() throws Exception {
        doReturn(Optional.of(provider.forClass(HttpScenario.class))).when(repository).get(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("getScenario.json"), ObjectNode.class);

        GraphQLResponse getScenario = testTemplate.perform(getRequestSourcePath("getScenario.graphql"), "getScenario", variables);

        getScenario
            .assertThatErrorsField().isNotPresent()
            .and()
            .assertThatJsonContent().isEqualToJson(getExpectedSourcePath("getScenario_httpScenario.json"), this.getClass());
    }

    @Test
    void getScenario_uuidValidationError() throws Exception {
        ObjectNode variables = provider.fromFile(getVariableSourcePath("getScenario_uuidValidationError.json"), ObjectNode.class);

        GraphQLResponse getScenario = testTemplate.perform(getRequestSourcePath("getScenario.graphql"), "getScenario", variables);

        getScenario
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: getScenario.scenarioUuid: must have UUID format]");
    }

    @Test
    void getScenario_noScenario() throws Exception {
        doReturn(Optional.empty()).when(repository).get(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("getScenario.json"), ObjectNode.class);

        GraphQLResponse getScenario = testTemplate.perform(getRequestSourcePath("getScenario.graphql"), "getScenario", variables);

        getScenario
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: Scenario with uuid 9161f68d-da9e-4fa2-b0ad-142bce3b4550 not found]");
    }
}
