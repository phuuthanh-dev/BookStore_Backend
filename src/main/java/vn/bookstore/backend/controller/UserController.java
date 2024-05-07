package vn.bookstore.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.bookstore.backend.model.User;
import vn.bookstore.backend.payload.request.LoginRequest;
import vn.bookstore.backend.payload.response.JwtResponse;
import vn.bookstore.backend.security.jwt.JwtUtils;
import vn.bookstore.backend.service.UserService;

import javax.naming.AuthenticationException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/active")
    public ResponseEntity<?> activeAccount(@RequestParam String email, @RequestParam String activationKey) {
        ResponseEntity<?> response = userService.activeAccount(email, activationKey);
        return response;
    }

}
