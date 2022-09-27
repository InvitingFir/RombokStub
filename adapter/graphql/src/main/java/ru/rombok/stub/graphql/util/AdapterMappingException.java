package ru.rombok.stub.graphql.util;

import static java.lang.String.format;

public class AdapterMappingException extends RuntimeException {
    private static final String SINGLE_OBJECT_MESSAGE = "Failed to map from %s to %s";
    private static final String LIST_MESSAGE = "Failed to map list of %s to list of %s";

    private AdapterMappingException(String msg, Exception e) {
        super(msg, e);
    }

    public static AdapterMappingExceptionBuilder builder() {
        return new AdapterMappingExceptionBuilder();
    }

    private static AdapterMappingException forObject(Class<?> source, Class<?> destination, Exception e) {
        return new AdapterMappingException(format(SINGLE_OBJECT_MESSAGE, source, destination), e);
    }

    private static AdapterMappingException forListOf(Class<?> source, Class<?> destination, Exception e) {
        return new AdapterMappingException(format(LIST_MESSAGE, source, destination), e);
    }

    public static class AdapterMappingExceptionBuilder {
        private Class<?> source;
        private Class<?> destination;
        private Exception e;

        public AdapterMappingExceptionBuilder from(Class<?> clazz) {
            this.source = clazz;
            return this;
        }

        public AdapterMappingExceptionBuilder to(Class<?> clazz) {
            this.destination = clazz;
            return this;
        }

        public AdapterMappingExceptionBuilder sourceException(Exception e) {
            this.e = e;
            return this;
        }

        public AdapterMappingException forSingleObject() {
            return AdapterMappingException.forObject(source, destination, e);
        }

        public AdapterMappingException forList() {
            return AdapterMappingException.forListOf(source, destination, e);
        }

    }
}
