package ru.rombok.stub.graphql.exception;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rombok.stub.api.scenario.exception.ScenarioNotFoundException;
import ru.rombok.stub.api.service.exception.ServiceNotFoundException;

import javax.validation.ConstraintViolationException;

@Component
@Slf4j
@RequiredArgsConstructor
public class GraphQLExceptionHandler {
    private final GraphQLErrorFactory factory;

    @ExceptionHandler(Exception.class)
    public ThrowableGraphQLError handle(Exception e) {
        log.info("", e);
        return new ThrowableGraphQLError(e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ThrowableGraphQLError handle(IllegalArgumentException e) {
        log.info("", e);
        return new ThrowableGraphQLError(e, HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @ExceptionHandler({ScenarioNotFoundException.class, ServiceNotFoundException.class, ConstraintViolationException.class})
    public ThrowableGraphQLError handle(RuntimeException e) {
        log.info("", e);
        return factory.mapToException(e);
    }
}
