package vn.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.backend.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

}
