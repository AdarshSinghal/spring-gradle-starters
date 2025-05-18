package com.ad.starter.graph.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class DbLifecycleManager {

    private final Neo4jWaiter neo4jWaiter;

    //Start DB when application is started
    @PostConstruct
    public void init() throws InterruptedException, IOException {
        log.info("Starting Neo4j DB");
        ProcessBuilder builder = new ProcessBuilder("bash", "StartDB.sh");
        builder.directory(new File("scripts"));
        builder.inheritIO();
        Process process = builder.start();
        process.waitFor();
        neo4jWaiter.waitForNeo4j(Duration.ofSeconds(30));
        log.info("Neo4j is ready to accept connections");
    }

    //Stop Neo4j DB when application is stopped
    @PreDestroy
    public void onShutdown() {
        log.info("Stopping Neo4j DB...");
        try {
            ProcessBuilder builder = new ProcessBuilder("bash", "StopDB.sh");
            builder.directory(new File("scripts"));
            builder.inheritIO(); // optional: shows output
            Process process = builder.start();
            process.waitFor();
            log.info("Neo4j DB stopped.");
        } catch (IOException | InterruptedException e) {
            log.error("Failed to stop Neo4j DB: {}", e.getMessage());
        }
    }

}
