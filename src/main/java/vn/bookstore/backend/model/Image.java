package vn.bookstore.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @Column(name = "icon")
    private boolean icon;

    @Column(name = "link")
    private String link;

    @Column(name = "data", columnDefinition = "nvarchar(MAX)")
    @Lob
    private String data;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;

//    @ManyToOne
//    @JoinColumn (name = "book_id")
//    @JsonBackReference
//    private Book book;
}
