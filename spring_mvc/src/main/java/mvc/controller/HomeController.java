package mvc.controller;

import mvc.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping({"/", "/home"})
    public User home() {
        return new User(1L, "Martin");
    }
}
