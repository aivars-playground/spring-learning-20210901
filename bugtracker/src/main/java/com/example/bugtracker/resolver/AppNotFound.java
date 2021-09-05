package com.example.bugtracker.resolver;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppNotFound extends RuntimeException implements GraphQLError {

    private final Map<String, Object> extensions = new HashMap<>();

    public AppNotFound(String message, Long  id) {
        super(message);
        extensions.put("invalidAppId",id);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

    @Override
    public List<Object> getPath() {
        return GraphQLError.super.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return GraphQLError.super.toSpecification();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
