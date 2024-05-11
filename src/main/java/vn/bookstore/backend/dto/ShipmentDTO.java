package vn.bookstore.backend.dto;

import lombok.Data;

@Data
public class ShipmentDTO {
    private Integer shipmentId;
    private String description;
    private String name;
    private double shippingPrice;
}
