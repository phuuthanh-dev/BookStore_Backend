package vn.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.backend.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
