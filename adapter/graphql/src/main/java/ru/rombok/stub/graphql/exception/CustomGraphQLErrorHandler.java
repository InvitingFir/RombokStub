package ru.rombok.stub.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream().map(this::filterParams).collect(Collectors.toList());
    }

    private GraphQLError filterParams(GraphQLError error) {
        return new GraphQLError() {
            @Override
            public String getMessage() {
                return error.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                return error.getLocations();
            }

            @Override
            public ErrorType getErrorType() {
                return error.getErrorType();
            }
        };
    }
}