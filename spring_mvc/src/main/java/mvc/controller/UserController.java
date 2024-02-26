package mvc.controller;

import mvc.model.User;
import mvc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User user(@PathVariable String id) {
        return userService.findById(Long.parseLong(id));
    }
}
