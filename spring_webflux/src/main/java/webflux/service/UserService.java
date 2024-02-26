package webflux.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.model.User;

import java.time.Duration;
import java.util.UUID;

@Service
public class UserService {
    private static final Duration DELAY = Duration.ofSeconds(1L);
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public Mono<User> findById(long id) {
        LOGGER.info("findById({})", id);
        User user = new User(id, UUID.randomUUID().toString());
        LOGGER.info("created a new User {}", user);
        return Mono.just(user).delaySubscription(DELAY);
    }

    public Flux<User> findAll() {
        LOGGER.info("findAll()");
        return Flux.range(1, 10).delayElements(DELAY).map(i -> {
            User user = new User(i, UUID.randomUUID().toString());
            LOGGER.info("created a new User {}", user);
            return user;
        });
    }
}
