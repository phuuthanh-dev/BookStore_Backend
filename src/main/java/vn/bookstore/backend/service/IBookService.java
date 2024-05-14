package vn.bookstore.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.bookstore.backend.dto.BookCreateRequest;
import vn.bookstore.backend.dto.UpdateBookResponse;
import vn.bookstore.backend.model.Book;


public interface IBookService {
    Book getBookById(int id);

    Page<Book> getAllBooks(Pageable pageable);

    Book saveBook(BookCreateRequest book);

//
UpdateBookResponse updateBook(UpdateBookResponse book);
//
//    void deleteBook(Long id);

}
