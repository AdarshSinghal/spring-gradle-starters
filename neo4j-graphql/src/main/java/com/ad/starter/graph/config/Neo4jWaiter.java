package com.ad.starter.graph.config;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
@Component
@Slf4j
public class Neo4jWaiter {

    @Value("${spring.neo4j.uri}")
    private String uri;

    @Value("${spring.neo4j.authentication.username}")
    private String username;

    @Value("${spring.neo4j.authentication.password}")
    private String password;

    @Value("${spring.neo4j.security.encrypted:false}")
    private boolean encrypted;

    public void waitForNeo4j(Duration timeout) throws InterruptedException {
        long start = System.currentTimeMillis();
        long timeoutMillis = timeout.toMillis();
        boolean connected = false;

        Config config;
        if(encrypted){
            config = Config.builder().withEncryption().build();
        } else {
            config = Config.builder().build();
        }

        int waitCounter=0;

        while (!connected && (System.currentTimeMillis() - start) < timeoutMillis) {
            try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password), config)) {
                try (Session session = driver.session()) {
                    session.run("RETURN 1").consume();
                    connected = true;
                }
            } catch (Exception e) {
                if(waitCounter==0){
                    log.info("Neo4j container is still not ready to accept connection...");
                }
                if(++waitCounter%4==0){
                    log.info("Waiting for Neo4j container to accept connection... (%d sec elapsed)".formatted(waitCounter/2));
                }
                Thread.sleep(500);
            }
        }

        if (!connected) {
            throw new RuntimeException("Neo4j did not become available within " + timeout.toSeconds() + " seconds");
        }
        Thread.sleep(500); // For safer side, wait little more.
        log.info("Neo4j is ready!");
    }
}
