package com.mdao07.restdemo;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This allows our server to accept TRACE requests
// It's better to not accept TRACE requests at all
@Configuration
public class TomcatConfiguration {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return customizer -> customizer.addConnectorCustomizers(connector -> {
            connector.setAllowTrace(true);
        });
    }
}