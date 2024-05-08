package vn.bookstore.backend.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.bookstore.backend.model.Role;
import vn.bookstore.backend.model.User;
import vn.bookstore.backend.service.UserService;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    public static final String SECRET = "sU8DJz3v27znN2iG2XesKP6e1qGQ7fp1P6XVJwg47K5tWTH1o61OwGn6X5zC6Kz9";

    @Autowired
    public UserService userService;

    // Tạo JWT dựa trên tn đăng nhập
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        User user = userService.findByUsername(username);
        boolean isAdmin = false;
        boolean isStaff = false;
        boolean isUser = false;
        if (user != null && user.getRoles().size() > 0) {
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (role.getName().equals("ADMIN")) {
                    isAdmin = true;
                }
                if (role.getName().equals("STAFF")) {
                    isStaff = true;
                }
                if (role.getName().equals("USER")) {
                    isUser = true;
                }
            }
        }

        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        claims.put("isUser", isUser);
        claims.put("username", username);
        return createToken(claims, username);
    }

    // Tạo JWT với các claim đã cho
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))   // JWT tự động hết hạn sau 30p
                .signWith(SignatureAlgorithm.HS256, key())
                .compact();
    }

    // Lấy secret key
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    // Extract username
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(key()).parseClaimsJws(token).getBody();
    }

    // Trích xuất thông tin cho 1 claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    // Check expiration from JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // check JWT expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Check valid token
    public boolean validateJwtToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
