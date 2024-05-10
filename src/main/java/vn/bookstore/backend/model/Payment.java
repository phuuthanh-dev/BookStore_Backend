package vn.bookstore.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="payment_id")
    private int id;

    @Column(name ="name", columnDefinition = "nvarchar(255)")
    private String name;

    @Column(name ="description", columnDefinition = "nvarchar(255)")
    private String description;

    @Column(name ="payment_price")
    private double paymentPrice;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH
    })
    @JsonManagedReference
    private List<Order> orders;
}
