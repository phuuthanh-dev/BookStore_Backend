package vn.bookstore.backend.dto;

import lombok.Data;


public record UpdateBookResponse(
        Long price,
        String name,
        String author,
        String isbn,
        Integer id,
        String description,
        Integer quantity,
        Long avgRatings) {
}
