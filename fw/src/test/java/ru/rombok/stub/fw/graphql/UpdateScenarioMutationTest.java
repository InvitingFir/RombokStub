package ru.rombok.stub.fw.graphql;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.HttpScenario;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@Disabled
class UpdateScenarioMutationTest extends AbstractGraphQLTest {

    @MockBean
    ScenarioRepository repository;

    @Override
    public String getTestSourceName() {
        return "UpdateScenario";
    }

    @Test
    void updateScenario() throws Exception {
        doReturn(Optional.of(provider.forClass(HttpScenario.class))).when(repository).get(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("updateScenario.json"), ObjectNode.class);

        GraphQLResponse updateScenario = testTemplate.perform(getRequestSourcePath("updateScenario.graphql"), "updateScenario", variables);

        updateScenario
            .assertThatErrorsField().isNotPresent()
            .and()
            .assertThatJsonContent().isEqualToJson(getExpectedSourcePath("updateScenario.json"), this.getClass());
    }

    @Test
    void updateScenario_uuidValidationError() throws Exception {
        ObjectNode variables = provider.fromFile(getVariableSourcePath("updateScenario_uuidValidationError.json"), ObjectNode.class);

        GraphQLResponse updateScenario = testTemplate.perform(getRequestSourcePath("updateScenario.graphql"), "updateScenario", variables);

        updateScenario
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: updateScenario.scenarioUuid: must have UUID format]");
    }

    @Test
    void updateScenario_noScenario() throws Exception {
        doReturn(Optional.empty()).when(repository).get(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("updateScenario.json"), ObjectNode.class);

        GraphQLResponse updateScenario = testTemplate.perform(getRequestSourcePath("updateScenario.graphql"), "updateScenario", variables);

        updateScenario
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: Scenario with uuid 9161f68d-da9e-4fa2-b0ad-142bce3b4550 not found]");
    }
}
