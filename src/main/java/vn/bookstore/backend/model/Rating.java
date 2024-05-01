package vn.bookstore.backend.model;

import lombok.Data;

@Data
public class Rating {
    private long id;
    private float ratingScore;
    private String comment;
    private Book book;
    private User user;
}
