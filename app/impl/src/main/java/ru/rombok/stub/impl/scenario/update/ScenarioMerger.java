package ru.rombok.stub.impl.scenario.update;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.update.ScenarioMergingException;
import ru.rombok.stub.domain.scenario.Scenario;

import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
@Component
public class ScenarioMerger {

    public Scenario merge(Scenario root, Scenario updated) {
        BeanUtils.copyProperties(updated, root, getNullProperties(updated));
        return root;
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private String[] getNullProperties(Scenario scenario) {
        return Arrays.stream(scenario.getClass().getDeclaredFields())
            .filter(field -> getFieldValue(scenario, field) == null)
            .map(Field::getName)
            .toArray(String[]::new);
    }

    private Object getFieldValue(Scenario scenario, Field field) {
        try {
            return field.get(scenario);
        } catch (IllegalAccessException e) {
            log.error("Failed to get field values for scenario with uuid = {}", scenario.getUuid());
            throw ScenarioMergingException.getFieldValue(scenario.getUuid(), e);
        }
    }
}
