package vn.bookstore.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.bookstore.backend.model.Book;


public interface IBookService {
    Book getBookById(int id);

    Page<Book> getAllBooks(Pageable pageable);

//    List<Book> getAllBooks();
//
//    Book saveBook(Book book);
//
//    Book updateBook(Book book);
//
//    void deleteBook(Long id);

}
