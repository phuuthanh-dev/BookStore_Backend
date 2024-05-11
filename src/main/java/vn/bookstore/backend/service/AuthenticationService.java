package vn.bookstore.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.bookstore.backend.dto.RegisterRequest;
import vn.bookstore.backend.exception.UserAlreadyExistsException;
import vn.bookstore.backend.model.Notification;
import vn.bookstore.backend.model.Role;
import vn.bookstore.backend.model.User;
import vn.bookstore.backend.repository.RoleRepository;
import vn.bookstore.backend.repository.UserRepository;
import vn.bookstore.backend.security.jwt.JwtUtils;
import vn.bookstore.backend.service.email.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    @Value("${spring.mail.username}")
    private String emailUsername;

    private String generateActivationCode() {
        return UUID.randomUUID().toString();
    }

    public ResponseEntity<?> register(RegisterRequest request)  {
        userRepository.findByUsername(request.username())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException(
                            "User with username: " + request.username() + " already in use!");
                });
        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException(
                            "User with email: " + request.email() + " already in use!");
                });

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
        var user = User.builder()
                .firstName(request.firstName())
                .email(request.email())
                .password(bCryptPasswordEncoder.encode(request.password()))
                .lastName(request.lastName())
                .gender(request.gender())
                .username(request.username())
                .activationCode(generateActivationCode())
                .isActivated(false)
                .phone(request.phone())
                .roles(roles)
                .build();

        User registeredUser = userRepository.save(user);
        sendEmailActivationCode(user.getEmail(), user.getActivationCode());
        return ResponseEntity.ok("Registered user successfully!");
    }

//    public AuthenticationResponse activateAccount(ActivateAccountRequest request) throws MessagingException {
//        var userEmail = jwtService.extractUsername(request.token());
//        var user = userRepository.findByEmail(userEmail)
//                .orElseThrow();
//        if (jwtService.isTokenExpired(request.token())) {
//            emailService.sendActivationEmail(userEmail, user.getFullName(), jwtService.generateToken(user));
//            throw new ExpiredtTokenException(
//                    "Email kích hoạt đã hết hạn. Vui lòng kiểm tra email để nhận hướng dẫn kích hoạt mới.");
//        } else {
//            user.setState(AccountState.ACTIVE);
//            userRepository.save(user);
//            return new AuthenticationResponse(request.token());
//        }
//    }

    public ResponseEntity<?> activeAccount(String email, String activationCode) {
        User user = userRepository.findUserByEmail(email);

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

//    public AuthenticationResponse authenticate(AuthenticationRequest request) throws MessagingException {
//        var user = userRepository.findByEmail(request.email())
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        "Người dùng với email: " + request.email()
//                                + " không tồn tại. \nVui lòng đăng ký tài khoản mới."));
//        if (user.getState() == AccountState.INACTIVE) {
//            emailService.sendActivationEmail(request.email(), user.getFullName(),
//                    jwtService.generateToken(user));
//            throw new AccountInactiveException("");
//        } else if (user.getState() == AccountState.DISABLE) {
//            throw new AccountDisabledException("");
//        } else if (user.getState() == AccountState.ACTIVE) {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.email(),
//                            request.password()));
//            var jwtToken = jwtService.generateToken(user);
//            return new AuthenticationResponse(jwtToken);
//        }
//        return null;
//    }

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

//    public void forgotPassword(ForgotPasswordRequest request) throws MessagingException {
//        var existingUser = userRepository.findByEmail(request.email())
//                .orElseThrow(() -> new UserNotFoundException(
//                        "Người dùng với email '" + request.email() + "' không tồn tại."));
//        if (existingUser.getRole() != Role.USER) {
//            throw new UnauthorizedException(
//                    "Không có quyền truy cập. Vui lòng liên hệ quản trị viên để được hỗ trợ.");
//        }
//        emailService.sendResetPasswordEmail(request.email(), existingUser.getFullName(),
//                jwtService.generateToken(existingUser));
//        return;
//    }
//
//    public AuthenticationResponse changePassword(ChangePasswordRequest request) {
//        var user = userRepository.findByEmail(jwtService.extractUsername(request.token()))
//                .orElseThrow();
//        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
//            throw new OldPasswordMismatchException("Mật khẩu cũ không đúng.");
//        }
//        user.setPassword(passwordEncoder.encode(request.newPassword()));
//        userRepository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return new AuthenticationResponse(jwtToken);
//    }
//
//    public AuthenticationResponse resetPassword(ResetPasswordRequest request) {
//        var userEmail = jwtService.extractUsername(request.token());
//        var user = userRepository.findByEmail(userEmail)
//                .orElseThrow();
//        if (user.getRole() == Role.USER) {
//            user.setPassword(passwordEncoder.encode(request.password()));
//            userRepository.save(user);
//            var jwtToken = jwtService.generateToken(user);
//            return new AuthenticationResponse(jwtToken);
//        } else {
//            throw new UnauthorizedException("Không có quyền truy cập.");
//        }
//    }
}