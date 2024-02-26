package mvc.service;

import mvc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class UserService {
    private static final long REQUEST_TIMEOUT = 1_000;
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public User findById(long id) {
        try {
            Thread.sleep(REQUEST_TIMEOUT);
        } catch (InterruptedException e) {
            LOGGER.warn("getUser({}) failed: {}", id, e.getMessage());
        }
        User user = new User(id, UUID.randomUUID().toString());
        LOGGER.info("created a user {}", user);
        return user;
    }
}
