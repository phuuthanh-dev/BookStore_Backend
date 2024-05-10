package vn.bookstore.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.bookstore.backend.model.Book;
import vn.bookstore.backend.repository.BookRepository;
import vn.bookstore.backend.repository.ImageRepository;

import java.util.List;

@Transactional
@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;


    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }


    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách tương ứng"));
    }

}
