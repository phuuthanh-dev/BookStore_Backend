package vn.bookstore.backend.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long orderItemId;
    private double price;
    private Integer quantity;
    private Integer bookId;
    private Integer orderId;
}
