package vn.bookstore.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import vn.bookstore.backend.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.category c WHERE c.id = :id")
    Page<Book> findByCategoryList_CategoryId(@Param("id") int id, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.category c WHERE c.id = :id AND b.name like %:name%")
    Page<Book> findByNameAndCategoryList_CategoryId(@Param("id") int id, @Param("name") String name, Pageable pageable);
}
