package mvc.controller;

import mvc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@RestController
public class RestTemplateController {
    private static final Logger LOGGER = LogManager.getLogger(RestTemplateController.class);

    /**
     * this method sends 100 requests simultaneously
     */
    @GetMapping("/test-user")
    public void findByIdTest() {
        List<CompletableFuture<User>> futures = IntStream.range(0, 100).boxed().map(i -> CompletableFuture.supplyAsync(() -> {
            RestTemplate restTemplate = new RestTemplate();
            long id = i + 1;
            return restTemplate.getForObject("http://localhost:8080/user/" + id, User.class);
        })).toList();
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allFutures.thenRun(() ->
                futures.forEach(future -> {
                    User user = future.join();
                    LOGGER.info("returned a user {}", user);
                })
        );
    }
}
