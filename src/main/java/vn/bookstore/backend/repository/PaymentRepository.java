package vn.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.backend.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
