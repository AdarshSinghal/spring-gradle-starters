package com.ad.starter.graph.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        // Get the active profile
        String activeProfile = environment.getProperty("spring.profiles.active", "local");

        // Determine the port based on the profile
        String port = "8500"; // Default port for 'local'
        if ("docker".equalsIgnoreCase(activeProfile)) {
            port = "8501";
        }
        log.info("GraphiQL UI is available at: http://localhost:{}/graphiql", port);
        log.info("Neo4j Graph UI available at http://localhost:7474 | neo4j/neo4jTest");
        log.info("For credentials or bolt server port, please refer to docker-compose.yml file");
    }

}
