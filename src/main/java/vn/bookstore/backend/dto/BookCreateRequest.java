package vn.bookstore.backend.dto;

public record BookCreateRequest (
     Integer bookId,
     String isbn,
     String author,
     float avgRatings,
     String description,
     String name,
     double originalPrice,
     double price,
     Integer quantity,
     String dataImage,
     boolean icon
) {}
