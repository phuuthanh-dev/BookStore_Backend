package vn.bookstore.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private int id;
    private String name;
    private String author;
    private String ISBN;
    private String description;
    private double originalPrice;
    private double price;
    private int quantity;
    private double rating;
    private List<Category> categories;
    private List<Image> images;
    private List<Rating> ratings;
    private List<OrderItem> orderItems;
    private List<CartItem> cartItems;
    private List<WishList> wishLists;
}
