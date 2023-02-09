package se.kth.iv1201.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Starts the themePark application.
 */
@SpringBootApplication
public class Main {
    //@Autowired
    //private ServerProperties serverProps;

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Main.class);

        app.run(args);
    }

   /* @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> getWebServerFactoryCustomizer() {
        //LOGGER.trace("Setting WebServerFactory.");
        return serverFactory -> {
            //LOGGER.trace("Setting context root.");
            serverFactory.setContextPath(serverProps.getContextRoot());
        };
    } */
}