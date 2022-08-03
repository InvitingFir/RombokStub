package ru.rombok.stub.fw.graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.rombok.stub.fw.StubApplication;
import ru.rombok.stub.fw.common.FrameworkTestObjectProvider;
import ru.rombok.stub.fw.config.GraphQlTestConfig;
import ru.rombok.stub.fw.config.ObjectMapperTestConfig;
import ru.rombok.stub.fw.config.PostgreSqlDatabaseContainer;
import ru.rombok.stub.fw.config.TestObjectConfig;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {ServletWebServerFactoryAutoConfiguration.class, GraphQlTestConfig.class, StubApplication.class, ObjectMapperTestConfig.class,
        TestObjectConfig.class}
)
@Testcontainers
public abstract class AbstractGraphQLTest {
    private static final String VARIABLE_SOURCE_PATH = "/graphql/variables/";
    private static final String EXPECTED_SOURCE_PATH = "/graphql/expected/";
    private static final String REQUEST_SOURCE_PATH = "/graphql/request/";
    @Autowired
    FrameworkTestObjectProvider provider;

    @Autowired
    GraphQLTestTemplate testTemplate;

    @Container
    public static final PostgreSqlDatabaseContainer CONTAINER = PostgreSqlDatabaseContainer.getInstance();

    public abstract String getTestSourceName();

    public String getVariableSourcePath(String fileName) {
        return getSourcePath(VARIABLE_SOURCE_PATH, fileName);
    }

    public String getRequestSourcePath(String fileName) {
        return getSourcePath(REQUEST_SOURCE_PATH, fileName);
    }

    public String getExpectedSourcePath(String fileName) {
        return getSourcePath(EXPECTED_SOURCE_PATH, fileName);
    }

    private String getSourcePath(String sourcePath, String fileName) {
        return sourcePath + getTestSourceName() + "/" + fileName;
    }
}
