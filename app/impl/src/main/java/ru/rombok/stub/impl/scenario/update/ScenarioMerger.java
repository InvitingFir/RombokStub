package ru.rombok.stub.impl.scenario.update;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.update.ScenarioMergingException;
import ru.rombok.stub.domain.scenario.Scenario;
import ru.rombok.stub.domain.var.ScenarioVariable;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.lang.Character.toLowerCase;
import static java.util.stream.IntStream.range;

@Slf4j
@Component
public class ScenarioMerger {
    private static final BiConsumer<ScenarioVariable, ScenarioVariable> updateName = (root, update) -> root.setName(update.getName());
    private static final BiConsumer<ScenarioVariable, ScenarioVariable> updateValue = (root, update) -> root.setValue(update.getValue());
    private static final String GETTER_PREFIX = "get";
    private static final String[] MANUALLY_UPDATED_PROPERTIES = {"variables"};

    public Scenario merge(Scenario root, Scenario updated) {
        assertIsNotNull(root);
        assertIsNotNull(updated);
        String[] nullProperties = getSkippedProperties(updated);
        BeanUtils.copyProperties(updated, root, nullProperties);
        mergeVariables(Optional.ofNullable(root.getVariables()).orElseGet(ArrayList::new), updated.getVariables());
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

    private String[] getSkippedProperties(Scenario scenario) {
        Predicate<Method> returnsVoid = mtd -> executeGetter(mtd, scenario) == null;
        return Stream.concat(
                gettersFor(scenario)
                    .filter(returnsVoid)
                    .map(Method::getName)
                    .map(toPropertyName()),
                Arrays.stream(MANUALLY_UPDATED_PROPERTIES))
            .toArray(String[]::new);
    }

    private Object executeGetter(Method method, Scenario scenario) {
        try {
            if (method.canAccess(scenario)) {
                return method.invoke(scenario);
            }
            throw ScenarioMergingException.forGetterInvocation(scenario.getUuid());
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

    private void mergeVariables(List<ScenarioVariable> root, List<ScenarioVariable> updates) {
        if (updates == null) {
            return;
        }
        int rootSize = root.size();
        int updatesSize = updates.size();
        int newSize = rootSize;
        if (updatesSize == 0) {
            root.clear();
            return;
        } else if (rootSize < updatesSize) {
            root.addAll(updates.subList(rootSize, updatesSize));
        } else if (rootSize > updatesSize) {
            root.retainAll(root.subList(updatesSize, rootSize));
            newSize = updatesSize;
        }
        range(0, newSize)
            .forEach(i -> updateName.andThen(updateValue).accept(root.get(i), updates.get(i)));
    }
}
