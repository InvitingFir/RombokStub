package ru.rombok.stub.graphql.exception.graphqlerror;

import graphql.ErrorType;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import ru.rombok.stub.api.scenario.exception.ScenarioNotFoundException;
import ru.rombok.stub.graphql.exception.GraphQLErrorFactory;

import java.util.function.Predicate;

@Component
public class ScenarioNotFoundGraphQLError implements GraphQLErrorFactory.GraphQLError {

    @Override
    public Predicate<Throwable> exceptionClass() {
        return ScenarioNotFoundException.class::isInstance;
    }

    @Override
    public ThrowableGraphQLError getError(Throwable e) {
        return new ThrowableGraphQLError(e) {

            @Override
            public ErrorType getErrorType() {
                return ErrorType.DataFetchingException;
            }
        };
    }
}
