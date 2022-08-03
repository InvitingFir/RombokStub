package ru.rombok.stub.fw.graphql;

import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.fw.StubApplication;
import ru.rombok.stub.fw.common.FrameworkTestObjectProvider;
import ru.rombok.stub.fw.config.GraphQlTestConfig;
import ru.rombok.stub.fw.config.ObjectMapperTestConfig;
import ru.rombok.stub.fw.config.TestObjectConfig;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@GraphQLTest(classes = {GraphQlTestConfig.class, StubApplication.class, ObjectMapperTestConfig.class, TestObjectConfig.class})
class ScenarioMutationTest {
    @Autowired
    FrameworkTestObjectProvider provider;

    @Autowired
    GraphQLTestTemplate testTemplate;

    @MockBean
    ScenarioRepository repository;

    @Test
    void query_getScenario() {
        doReturn(provider.forClass(Scenario.class)).when(repository).get(any());

        //        testTemplate.perform()

    }
}
