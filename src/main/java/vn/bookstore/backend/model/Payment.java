package vn.bookstore.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class Payment {
    private int id;
    private String name;
    private String description;
    private double paymentPrice;
    private List<Order> orders;
}
