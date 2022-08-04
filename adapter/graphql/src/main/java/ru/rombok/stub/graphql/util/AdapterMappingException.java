package ru.rombok.stub.graphql.util;

import static java.lang.String.format;

public class AdapterMappingException extends RuntimeException {
    private static final String SINGLE_OBJECT_MESSAGE = "Failed to map from %s to %s";
    private static final String LIST_MESSAGE = "Failed to map list of %s to list of %s";

    private AdapterMappingException(String msg, Exception e) {
        super(msg, e);
    }

    public static AdapterMappingException forObject(Class<?> source, Class<?> destination, Exception e) {
        return new AdapterMappingException(format(SINGLE_OBJECT_MESSAGE, source, destination), e);
    }

    public static AdapterMappingException forListOf(Class<?> source, Class<?> destination, Exception e) {
        return new AdapterMappingException(format(LIST_MESSAGE, source, destination), e);
    }
}
