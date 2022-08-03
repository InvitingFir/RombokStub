package ru.rombok.stub.graphql.exception;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class GraphQLErrorFactory {
    private final List<GraphQLError> errors;

    public ThrowableGraphQLError mapToException(Throwable e) {
        return findErrorForException(e).getError(e);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private GraphQLError findErrorForException(Throwable e) {
        return errors.stream()
            .filter(graphQLError -> graphQLError.exceptionClass().test(e))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(format("No suitable GraphQLError for %s exception", e.getClass())));
    }

    public interface GraphQLError {

        Predicate<Throwable> exceptionClass();

        ThrowableGraphQLError getError(Throwable e);
    }
}
