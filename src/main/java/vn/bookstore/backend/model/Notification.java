package vn.bookstore.backend.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }
}
