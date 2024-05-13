package vn.bookstore.backend.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.bookstore.backend.dto.LoginResponse;
import vn.bookstore.backend.dto.RegisterRequest;
import vn.bookstore.backend.enums.AccountState;
import vn.bookstore.backend.model.Notification;
import vn.bookstore.backend.payload.request.LoginRequest;
import vn.bookstore.backend.payload.response.JwtResponse;
import vn.bookstore.backend.security.jwt.JwtUtils;
import vn.bookstore.backend.service.AuthenticationService;
import vn.bookstore.backend.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) throws MessagingException {
        ResponseEntity<?> response = authenticationService.register(request);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Ktra xác thực
        System.out.println(loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        if (userService.findByUsername(loginRequest.getUsername()).getState() == AccountState.INACTIVE) {
            return ResponseEntity.status(202).body(new Notification("Your account is inactive, you need check email to active your account!"));
        }
        // Xác thực k thành công
        if (authentication.isAuthenticated()) {
            final String jwt = jwtUtils.generateToken(loginRequest.getUsername());

            return ResponseEntity.ok(new LoginResponse(jwt));
        }

        return ResponseEntity.badRequest().body("Username or password is incorrect");
    }

    @GetMapping("/active")
    public ResponseEntity<?> activeAccount(@RequestParam String email, @RequestParam String activationKey) {
        ResponseEntity<?> response = authenticationService.activeAccount(email, activationKey);
        return response;
    }

}
