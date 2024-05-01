package vn.bookstore.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="shipment_id")
    private int shipmentId;

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name ="shipping_price")
    private double shippingPrice;

    @OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH
    })
    private List<Order> orders;
}
