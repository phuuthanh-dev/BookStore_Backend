package vn.bookstore.backend.dto;

import lombok.Data;

@Data
public class ImageDTO {
    private Integer imageId;
    private String data;
    private Boolean icon;
    private String link;
    private String name;
    private Integer bookId;
}
