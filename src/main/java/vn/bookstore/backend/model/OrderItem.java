package vn.bookstore.backend.model;

import lombok.Data;

@Data
public class OrderItem {
    private long id;
    private int quantity;
    private double price;
    private Book book;
    private Order order;
}
