package vn.bookstore.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.bookstore.backend.model.Notification;
import vn.bookstore.backend.model.User;
import vn.bookstore.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(new Notification("This username is already in use!"));
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new Notification("This email is already in use!"));
        }
        User registeredUser = userRepository.save(user);
        return ResponseEntity.ok("Registered user successfully!");
    }
}
