package vn.bookstore.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.bookstore.backend.dto.BookCreateRequest;
import vn.bookstore.backend.model.Book;
import vn.bookstore.backend.model.Image;
import vn.bookstore.backend.repository.BookRepository;
import vn.bookstore.backend.repository.ImageRepository;

import java.util.List;

@Transactional
@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }


    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách tương ứng"));
    }

    @Override
    public Book saveBook(BookCreateRequest book) {
        Book book1 = new Book();
        book1.setAuthor(book.author());
        book1.setISBN(book.isbn());
        book1.setAvgRatings(book.avgRatings());
        book1.setPrice(book.price());
        book1.setOriginalPrice(book.originalPrice());
        book1.setQuantity(book.quantity());
        book1.setDescription(book.description());
        book1.setName(book.name());

        Image image = new Image();
        image.setBook(book1);
        image.setData(book.dataImage());
        image.setIcon(book.icon());

        imageRepository.save(image);
        return bookRepository.save(book1);
    }


}
