package ru.rombok.stub.graphql.exception.graphqlerror;

import graphql.ErrorType;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import ru.rombok.stub.graphql.exception.GraphQLErrorFactory;

import javax.validation.ConstraintViolationException;
import java.util.function.Predicate;

@Component
public class ConstraintViolationGraphQLError implements GraphQLErrorFactory.GraphQLError {

    @Override
    public Predicate<Throwable> exceptionClass() {
        return ConstraintViolationException.class::isInstance;
    }

    @Override
    public ThrowableGraphQLError getError(Throwable e) {
        return new ThrowableGraphQLError(e) {

            @Override
            public ErrorType getErrorType() {
                return ErrorType.ValidationError;
            }
        };
    }
}
