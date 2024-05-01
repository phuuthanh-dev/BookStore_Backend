package vn.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.backend.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
