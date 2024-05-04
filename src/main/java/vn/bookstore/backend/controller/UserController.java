package vn.bookstore.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.bookstore.backend.model.User;
import vn.bookstore.backend.service.UserService;

@RestController
@RequestMapping(name = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(name = "/register")
    public ResponseEntity<?> registerUser(@Validated  @RequestBody User user) {
        ResponseEntity<?> response = userService.registerUser(user);
        return response;
    }
}
