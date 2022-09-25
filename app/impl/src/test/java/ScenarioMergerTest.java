import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rombok.stub.domain.file.File;
import ru.rombok.stub.domain.scenario.HttpScenario;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.impl.scenario.update.ScenarioMerger;
import ru.rombok.stub.test.TestObjectProvider;

import static ru.rombok.stub.test.RombokAssertions.assertEqualsToFile;

class ScenarioMergerTest {
    private TestObjectProvider objectProvider;

    private ScenarioMerger scenarioMerger;

    @BeforeEach
    void setup() {
        scenarioMerger = new ScenarioMerger();
        objectProvider = new TestObjectProvider();
    }

    @Test
    void merge_partiallyNull() {
        Scenario rootScenario = objectProvider.fromFile("/common/httpScenario.json", HttpScenario.class);
        Scenario scenarioUpdate = new HttpScenario();
        scenarioUpdate.setPredicate(new File());
        scenarioUpdate.setIsDefault(true);
        scenarioUpdate.setVariables(null);

        Scenario actualScenario = scenarioMerger.merge(rootScenario, scenarioUpdate);

        assertEqualsToFile(actualScenario, "/ScenarioMergeTest/merge_partiallyNull_expected.json");
    }

    @Test
    void merge_allPropertiesAreNull() {
        Scenario rootScenario = objectProvider.fromFile("/common/httpScenario.json", HttpScenario.class);

        Scenario actualScenario = scenarioMerger.merge(rootScenario, new HttpScenario());

        assertEqualsToFile(actualScenario, "/common/httpScenario.json");
    }

    @Test
    void merge_allNotNull() {
        Scenario rootScenario = objectProvider.fromFile("/common/httpScenario.json", HttpScenario.class);
        Scenario scenarioUpdate = objectProvider.fromFile("/ScenarioMergeTest/merge_allNotNull_scenarioUpdates.json", HttpScenario.class);

        Scenario actualScenario = scenarioMerger.merge(rootScenario, scenarioUpdate);

        assertEqualsToFile(actualScenario, "/ScenarioMergeTest/merge_allNotNull_scenarioUpdates.json");
    }

    @Test
    void merge_varsUpdated_add() {
        Scenario rootScenario = objectProvider.fromFile("/common/httpScenario.json", HttpScenario.class);
        Scenario scenarioUpdate = objectProvider.fromFile("/ScenarioMergeTest/merge_varsUpdated_add.json", HttpScenario.class);

        Scenario actualScenario = scenarioMerger.merge(rootScenario, scenarioUpdate);

        assertEqualsToFile(actualScenario, "/ScenarioMergeTest/merge_varsUpdated_add.json");
    }

    @Test
    void merge_varsUpdated_remove() {
        Scenario rootScenario = objectProvider.fromFile("/common/httpScenario.json", HttpScenario.class);
        Scenario scenarioUpdate = objectProvider.fromFile("/ScenarioMergeTest/merge_varsUpdated_remove.json", HttpScenario.class);

        Scenario actualScenario = scenarioMerger.merge(rootScenario, scenarioUpdate);

        assertEqualsToFile(actualScenario, "/ScenarioMergeTest/merge_varsUpdated_remove.json");
    }

    @Test
    void merge_varsUpdated_equal() {
        Scenario rootScenario = objectProvider.fromFile("/common/httpScenario.json", HttpScenario.class);
        Scenario scenarioUpdate = objectProvider.fromFile("/ScenarioMergeTest/merge_varsUpdated_equal.json", HttpScenario.class);

        Scenario actualScenario = scenarioMerger.merge(rootScenario, scenarioUpdate);

        assertEqualsToFile(actualScenario, "/ScenarioMergeTest/merge_varsUpdated_equal.json");
    }
}
