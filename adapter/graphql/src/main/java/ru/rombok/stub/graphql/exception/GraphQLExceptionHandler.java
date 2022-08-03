package ru.rombok.stub.graphql.exception;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rombok.stub.api.scenario.exception.ScenarioNotFoundException;

import javax.validation.ConstraintViolationException;

@Component
@RequiredArgsConstructor
public class GraphQLExceptionHandler {
    private final GraphQLErrorFactory factory;

    @ExceptionHandler(Exception.class)
    public ThrowableGraphQLError handle(Exception e) {
        return new ThrowableGraphQLError(e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ThrowableGraphQLError handle(IllegalArgumentException e) {
        return new ThrowableGraphQLError(e, HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @ExceptionHandler({ScenarioNotFoundException.class, ConstraintViolationException.class})
    public ThrowableGraphQLError handle(RuntimeException e) {
        return factory.mapToException(e);
    }
}
