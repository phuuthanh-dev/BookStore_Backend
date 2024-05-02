package vn.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import vn.bookstore.backend.model.OrderItem;

@RepositoryRestResource(path = "order-items")
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
