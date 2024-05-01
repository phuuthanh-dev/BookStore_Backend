package vn.bookstore.backend.model;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Order {
    private int id;
    private Date date;
    private String purchaseAddress;
    private String deliveryAddress;
    private double totalPrice;
    private double shippingPrice;
    private double paymentPrice;
    private List<OrderItem> items;
    private User user;
    private Payment payment;
    private Shipment status;
}
