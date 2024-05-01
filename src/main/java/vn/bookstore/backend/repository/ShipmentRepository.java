package vn.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.backend.model.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

}
