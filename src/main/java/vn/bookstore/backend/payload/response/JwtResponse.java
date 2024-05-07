package vn.bookstore.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwt;
//    private Long id;
//    private String username;
//    private String email;
//    private List<String> roles;
}
