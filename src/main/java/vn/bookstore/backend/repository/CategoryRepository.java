package vn.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.backend.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
