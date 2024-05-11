package vn.bookstore.backend.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private Long ratingId;
    private String comment;
    private double ratingScore;
    private Integer bookId;
    private Integer userId;
}
