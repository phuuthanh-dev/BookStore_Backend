package vn.bookstore.backend.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Integer paymentId;
    private String description;
    private String name;
    private double paymentPrice;
}
