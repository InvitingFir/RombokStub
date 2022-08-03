package ru.rombok.stub.graphql.exception;

import graphql.ErrorType;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rombok.stub.api.scenario.exception.ScenarioNotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Map;

@Component
public class GraphQLExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError handle(RuntimeException e) {
        return new ThrowableGraphQLError(e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(ScenarioNotFoundException.class)
    public ThrowableGraphQLError handle(ScenarioNotFoundException e) {
        return new ThrowableGraphQLError(e, HttpStatus.BAD_REQUEST.getReasonPhrase()) {
            @Override
            public ErrorType getErrorType() {
                return ErrorType.DataFetchingException;
            }
        };
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ThrowableGraphQLError handle(ConstraintViolationException e) {
        return new ThrowableGraphQLError(e, HttpStatus.BAD_REQUEST.getReasonPhrase()) {
            @Override
            public ErrorType getErrorType() {
                return ErrorType.ValidationError;
            }

            @Override
            public Map<String, Object> getExtensions() {
                return Map.of("code", "UUID_VALIDATION");
            }
        };
    }
}
