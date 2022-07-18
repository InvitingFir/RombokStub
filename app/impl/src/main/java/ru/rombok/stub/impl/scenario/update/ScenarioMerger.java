package ru.rombok.stub.impl.scenario.update;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.update.ScenarioMergingException;
import ru.rombok.stub.domain.scenario.Scenario;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Predicate;
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

    private void assertIsNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Scenario merging arguments must not be null");
        }
    }

    private String[] getNullProperties(Scenario scenario) {
        Predicate<Method> returnsVoid = mtd -> executeGetter(mtd, scenario) == null;
        return gettersFor(scenario)
            .filter(returnsVoid)
            .map(Method::getName)
            .map(toPropertyName())
            .toArray(String[]::new);
    }

    private Object executeGetter(Method method, Scenario scenario) {
        try {
            if (method.canAccess(scenario)) {
                return method.invoke(scenario);
            } else {
                throw ScenarioMergingException.forGetterInvocation(scenario.getUuid());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Failed to get field values for scenario with uuid = {}", scenario.getUuid());
            throw ScenarioMergingException.forGetterInvocation(scenario.getUuid(), e);
        }
    }

    private Stream<Method> gettersFor(Scenario scenario) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(scenario.getClass()))
            .map(PropertyDescriptor::getReadMethod);
    }

    private UnaryOperator<String> toPropertyName() {
        return name -> {
            String get = name.replaceFirst(GETTER_PREFIX, "");
            char[] chars = get.toCharArray();
            chars[0] = toLowerCase(chars[0]);
            return new String(chars);
        };
    }
}
