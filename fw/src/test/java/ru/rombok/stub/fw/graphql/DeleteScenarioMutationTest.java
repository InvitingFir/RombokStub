package ru.rombok.stub.fw.graphql;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.HttpScenario;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

class DeleteScenarioMutationTest extends AbstractGraphQLTest {

    @MockBean
    ScenarioRepository repository;

    @Override
    public String getTestSourceName() {
        return "DeleteScenario";
    }

    @Test
    void deleteScenario() throws Exception {
        doAnswer(answer -> Optional.of(provider.forClass(HttpScenario.class))).when(repository).delete(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("deleteScenario.json"), ObjectNode.class);

        GraphQLResponse deleteScenario = testTemplate.perform(getRequestSourcePath("deleteScenario.graphql"), "deleteScenario", variables);

        deleteScenario
            .assertThatErrorsField().isNotPresent()
            .and()
            .assertThatJsonContent().isEqualToJson(getExpectedSourcePath("deleteScenario.json"), this.getClass());
    }

    @Test
    void deleteScenario_uuidValidationError() throws Exception {
        ObjectNode variables = provider.fromFile(getVariableSourcePath("deleteScenario_uuidValidationError.json"), ObjectNode.class);

        GraphQLResponse deleteScenario = testTemplate.perform(getRequestSourcePath("deleteScenario.graphql"), "deleteScenario", variables);

        deleteScenario
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: deleteScenario.uuid: must have UUID format]");
    }

    @Test
    void deleteScenario_noScenario() throws Exception {
        doReturn(Optional.empty()).when(repository).delete(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("deleteScenario.json"), ObjectNode.class);

        GraphQLResponse deleteScenario = testTemplate.perform(getRequestSourcePath("deleteScenario.graphql"), "deleteScenario", variables);

        deleteScenario
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: Scenario with uuid 9161f68d-da9e-4fa2-b0ad-142bce3b4550 not found]");
    }
}
