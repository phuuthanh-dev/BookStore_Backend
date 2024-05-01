package vn.bookstore.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private char gender;
    private String email;
    private String phone;
    private String purchaseAddress;
    private String deliveryAddress;
    private List<Rating> ratings;
    private List<WishList> wishlists;
    private List<Role> roles;
    private List<Order> orders;
}
