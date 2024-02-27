package webflux.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.model.User;

import java.util.stream.IntStream;

@RestController
public class WebClientController {
    private static final Logger LOGGER = LogManager.getLogger(WebClientController.class);

    @GetMapping("/user-test")
    public void findByIdTest() {
        WebClient client = WebClient.create("http://localhost:8080");
        IntStream.range(1, 100).boxed().forEach(i -> {
            Mono<User> user = client.get()
                    .uri("/user/{id}", i)
                    .retrieve()
                    .bodyToMono(User.class);
            user.subscribe(LOGGER::info);
        });
    }

    @GetMapping("/users-test")
    public Flux<User> findAllTest() {
        WebClient client = WebClient.create("http://localhost:8080");
        Flux<User> users = client.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class);
        users.subscribe(LOGGER::info);
        return users;
    }
}
