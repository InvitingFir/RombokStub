package ru.rombok.stub.fw.graphql;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.rombok.stub.api.service.repository.ServiceRepository;
import ru.rombok.stub.domain.service.HttpService;
import ru.rombok.stub.impl.common.UuidUtils;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class CreateScenarioMutationTest extends AbstractGraphQLTest {

    public static final String RANDOM_UUID = "9161f68d-da9e-4fa2-b0ad-142bce3b4550";
    @MockBean
    ServiceRepository repository;
    @SpyBean
    UuidUtils uuidUtils;

    @Override
    public String getTestSourceName() {
        return "CreateScenario";
    }

    @Test
    void createScenario() throws Exception {
        doReturn(UUID.fromString(RANDOM_UUID)).when(uuidUtils).randomUuid();
        doReturn(Optional.of(provider.forClass(HttpService.class))).when(repository).get(any());
        doReturn(null).when(repository).save(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("createScenario.json"), ObjectNode.class);

        GraphQLResponse createScenario = testTemplate.perform(getRequestSourcePath("createScenario.graphql"), "createScenario", variables);

        verify(repository).save(any());
        createScenario
            .assertThatErrorsField().isNotPresent()
            .and()
            .assertThatJsonContent().isEqualToJson(getExpectedSourcePath("createScenario.json"), this.getClass());
    }

    @Test
    void createScenario_uuidValidationError() throws Exception {
        ObjectNode variables = provider.fromFile(getVariableSourcePath("createScenario_uuidValidationError.json"), ObjectNode.class);

        GraphQLResponse createScenario = testTemplate.perform(getRequestSourcePath("createScenario.graphql"), "createScenario", variables);

        createScenario
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: createScenario.serviceUuid: must have UUID format]");
    }

    @Test
    void createScenario_noService() throws Exception {
        doReturn(Optional.empty()).when(repository).get(any());
        ObjectNode variables = provider.fromFile(getVariableSourcePath("createScenario.json"), ObjectNode.class);

        GraphQLResponse createScenario = testTemplate.perform(getRequestSourcePath("createScenario.graphql"), "createScenario", variables);

        createScenario
            .assertThatListOfErrors()
            .hasToString("[<Unspecified error>: Service with uuid 9161f68d-da9e-4fa2-b0ad-142bce3b4550 not found]");
    }
}
