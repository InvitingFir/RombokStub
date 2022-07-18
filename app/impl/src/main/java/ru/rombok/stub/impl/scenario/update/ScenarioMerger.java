package ru.rombok.stub.impl.scenario.update;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.update.ScenarioMergingException;
import ru.rombok.stub.domain.scenario.Scenario;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.lang.Character.toLowerCase;

@Slf4j
@Component
public class ScenarioMerger {
    private static final String GETTER_PREFIX = "get";

    public Scenario merge(Scenario root, Scenario updated) {
        assertIsNotNull(root);
        assertIsNotNull(updated);
        String[] nullProperties = getNullProperties(updated);
        BeanUtils.copyProperties(updated, root, nullProperties);
        return root;
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private String[] getNullProperties(Scenario scenario) {
        return Arrays.stream(scenario.getClass().getMethods())
            .flatMap(getVoidGetter(scenario))
            .map(Method::getName)
            .map(getterToProperty())
            .toArray(String[]::new);
    }

    private Function<Method, Stream<Method>> getVoidGetter(Scenario scenario) {
        return method -> Stream.of(method)
            .filter(mtd -> mtd.getName().startsWith(GETTER_PREFIX))
            .filter(mtd -> executeGetter(mtd, scenario) == null);
    }

    private Object executeGetter(Method method, Scenario scenario) {
        try {
            if (method.canAccess(scenario)) {
                return method.invoke(scenario);
            } else {
                throw ScenarioMergingException.invokeGetter(scenario.getUuid());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Failed to get field values for scenario with uuid = {}", scenario.getUuid());
            throw ScenarioMergingException.invokeGetter(scenario.getUuid(), e);
        }
    }

    private UnaryOperator<String> getterToProperty() {
        return name -> {
            String get = name.replaceFirst(GETTER_PREFIX, "");
            char[] chars = get.toCharArray();
            chars[0] = toLowerCase(chars[0]);
            return new String(chars);
        };
    }

    private void assertIsNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Scenario merging arguments must not be null");
        }
    }
}
