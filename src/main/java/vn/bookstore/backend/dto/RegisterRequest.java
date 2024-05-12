package vn.bookstore.backend.dto;

import lombok.Data;
import vn.bookstore.backend.model.Role;

public record RegisterRequest(
        String username,
        String firstName,
        String lastName,
        String password,
        String phone,
        String email,
        int gender
) {
}