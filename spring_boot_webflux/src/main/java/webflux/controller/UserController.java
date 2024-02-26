package webflux.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.model.User;
import webflux.service.UserService;

@RestController
public class UserController {
    private final UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public Mono<User> findById(@PathVariable String id) {
        LOGGER.info("findById({})", id);
        return userService.findById(Long.parseLong(id));
    }

    @GetMapping("/users")
    public Flux<User> findAll() {
        LOGGER.info("findAll()");
        return userService.findAll();
    }
}
