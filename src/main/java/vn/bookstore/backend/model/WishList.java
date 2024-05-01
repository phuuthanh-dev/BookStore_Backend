package vn.bookstore.backend.model;

import lombok.Data;

@Data
public class WishList {
    private long id;
    private User user;
    private Book book;
}
