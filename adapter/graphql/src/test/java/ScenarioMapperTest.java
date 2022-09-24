import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rombok.stub.domain.scenario.HttpScenario;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.graphql.scenario.map.ScenarioMapper;
import ru.rombok.stub.graphql.scenario.request.ScenarioRequest;
import ru.rombok.stub.graphql.scenario.response.ScenarioResponse;
import ru.rombok.stub.test.TestObjectProvider;

import java.util.List;

import static ru.rombok.stub.test.RombokAssertions.assertEqualsToFile;

class ScenarioMapperTest {
    private static final TypeReference<List<Scenario>> SCENARIO_LIST_REFERENCE = new TypeReference<>() {
    };
    private TestObjectProvider objectProvider;

    private ScenarioMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ScenarioMapper();
        objectProvider = new TestObjectProvider();
    }

    @Test
    void fromDto_httpScenario() {
        ScenarioRequest httpRequest = objectProvider.fromFile("/ScenarioMapperTest/fromDto_httpScenario_source.json", ScenarioRequest.class);

        Scenario scenario = mapper.fromDto(httpRequest);

        assertEqualsToFile(scenario, "/ScenarioMapperTest/fromDto_httpScenario_expected.json");
    }

    @Test
    void toDto_httpScenario() {
        Scenario httpScenario = objectProvider.fromFile("/ScenarioMapperTest/toDto_httpScenario_source.json", HttpScenario.class);

        ScenarioResponse scenarioResponse = mapper.toDto(httpScenario);

        assertEqualsToFile(scenarioResponse, "/ScenarioMapperTest/toDto_httpScenario_expected.json");
    }

    @Test
    void toDto_list() {
        List<Scenario> scenarios = objectProvider.fromFile("/ScenarioMapperTest/toDto_list_source.json", SCENARIO_LIST_REFERENCE);

        List<ScenarioResponse> scenarioResponse = mapper.toDto(scenarios);

        assertEqualsToFile(scenarioResponse, "/ScenarioMapperTest/toDto_list_expected.json");
    }
}
