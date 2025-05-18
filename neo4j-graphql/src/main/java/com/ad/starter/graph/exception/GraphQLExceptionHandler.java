package com.ad.starter.graph.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.exceptions.ServiceUnavailableException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(@NonNull Throwable ex, @NonNull DataFetchingEnvironment env) {
        if (ex instanceof ServiceUnavailableException) {
            log.info(ex.getMessage());

            return GraphqlErrorBuilder.newError()
                    .message("Neo4j is down! Restarting...")
                    .path(env.getExecutionStepInfo().getPath())
                    .build();
        }

        return GraphqlErrorBuilder.newError()
                .message("An unexpected error occurred")
                .path(env.getExecutionStepInfo().getPath())
                .build();
    }
}
