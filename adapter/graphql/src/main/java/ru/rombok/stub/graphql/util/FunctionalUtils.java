package ru.rombok.stub.graphql.util;

import java.util.function.Function;

public class FunctionalUtils {

    public static <T, U> Function<T, U> first(Function<T, U> first) {
        return first;
    }
}
