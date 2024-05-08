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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User user) {
        ResponseEntity<?> response = userService.registerUser(user);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Ktra xác thực
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Xác thực k thành công
        if (authentication.isAuthenticated()) {
            final String jwt = jwtUtils.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt));
        }

        return ResponseEntity.badRequest().body("Username or password is incorrect");
    }
}
