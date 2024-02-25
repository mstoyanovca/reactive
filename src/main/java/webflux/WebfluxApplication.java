package webflux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebfluxApplication {
    private static final Logger LOGGER = LogManager.getLogger(WebfluxApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebfluxApplication.class, args);
        GreetingClient greetingClient = context.getBean(GreetingClient.class);
        // We need to block for the content here, or the JVM might exit before the message is logged:
        LOGGER.info(">> message = " + greetingClient.getMessage().block());
    }
}
