package vn.bookstore.backend.model;

import lombok.Data;

@Data
public class Image {
    private int id;
    private String name;
    private boolean icon;
    private String link;
    private String data;
    private Book book;
}
