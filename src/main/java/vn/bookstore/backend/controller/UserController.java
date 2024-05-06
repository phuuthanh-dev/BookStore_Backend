package vn.bookstore.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.bookstore.backend.model.User;
import vn.bookstore.backend.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated  @RequestBody User user) {
        ResponseEntity<?> response = userService.registerUser(user);
        return response;
    }

    @GetMapping("/hello")
    public String registerUser() {
        return "hello";
    }
}
