package vn.bookstore.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vn.bookstore.backend.model.Notification;
import org.springframework.stereotype.Service;
import vn.bookstore.backend.model.Role;
import vn.bookstore.backend.model.User;
import vn.bookstore.backend.repository.RoleRepository;
import vn.bookstore.backend.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;
    @Value("${spring.mail.username}")
    private String emailUsername;

    public ResponseEntity<?> registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(new Notification("This username is already in use!"));
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new Notification("This email is already in use!"));
        }

        // Encrypt password
        String encryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);

        // Set active code for user
        user.setActivationCode(generateActivationCode());
        user.setActivated(false);

        // Save to database
        User registeredUser = userRepository.save(user);

        // Send activation code to user email
        sendEmailActivationCode(user.getEmail(), user.getActivationCode());

        return ResponseEntity.ok("Registered user successfully!");
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        UserDetails user1 = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), rolesToAuthorities(user.getRoles()));
        return user1;
    }

    private String generateActivationCode() {
        return UUID.randomUUID().toString();
    }

    private void sendEmailActivationCode(String email, String activationCode) {
        String subject = "Activate your account at BookStore";
        String url = "http://localhost:3000/user/active/"+ email +"/"+activationCode;
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Activation Code</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #fff;\n" +
                "            padding: 40px;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        h1 {\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        p {\n" +
                "            margin-bottom: 20px;\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "        .activation-code {\n" +
                "            background-color: #f0f0f0;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 24px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .mt-20 {\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>" + subject + "</h1>\n" +
                "        <p>Please use the following code to activate your account:</p>\n" +
                "        <div class=\"activation-code\">" + activationCode + "</div>\n" +
                "        <div>To complete your registration, click the link below:</div>" +
                "        <a href="+ url + ">Confirm your account</a>\n" +
                "        <div class=\"mt-20\">Yours truly,</div>" +
                "        <div>Book Store Team</br></div>" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendEmail(emailUsername, email, subject, body);
    }

    public ResponseEntity<?> activeAccount(String email, String activationCode) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body(new Notification("User not found!"));
        }

        if (user.isActivated()) {
            return ResponseEntity.badRequest().body(new Notification("Account is already activated!"));
        }

        if (user.getActivationCode().equals(activationCode)) {
            user.setActivated(true);
            User user1 = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(new Notification("Account activated successfully!"));
        } else {
            return ResponseEntity.badRequest().body(new Notification("Account activation failed!"));
        }
    }
}
