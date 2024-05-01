package vn.bookstore.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class Shipment {
    private int id;
    private String name;
    private String description;
    private double shippingPrice;
    private List<Order> orders;
}
