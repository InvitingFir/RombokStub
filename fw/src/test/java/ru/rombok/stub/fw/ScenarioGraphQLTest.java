package ru.rombok.stub.fw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.rombok.stub.api.scenario.repository.ScenarioRepository;
import ru.rombok.stub.fw.config.GraphQlTestConfig;
import ru.rombok.stub.fw.config.ObjectMapperTestConfig;
import ru.rombok.stub.fw.config.TestObjectConfig;
import ru.rombok.stub.test.TestObjectProvider;

@SpringBootTest(classes = {GraphQlTestConfig.class, StubApplication.class, ObjectMapperTestConfig.class, TestObjectConfig.class})
@ActiveProfiles({"test", "local"})
class ScenarioGraphQLTest {
    @Autowired
    TestObjectProvider provider;
    //    @Autowired
    //    HttpGraphQlTester tester;
    @MockBean
    ScenarioRepository repository;

    @Test
    void query_getScenario() {

    }
}
