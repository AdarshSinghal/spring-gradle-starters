package com.pg;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@AllArgsConstructor
@Slf4j
public class ApplicationConfig {

    @EventListener
    public void handleWebServerReady(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        String baseUrl = "http://localhost:" + port;
        log.info("📚 Swagger UI: {}/swagger-ui/index.html", baseUrl);
    }
}
