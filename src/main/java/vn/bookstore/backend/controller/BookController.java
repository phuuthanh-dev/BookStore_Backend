package vn.bookstore.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.bookstore.backend.dto.BookCreateRequest;
import vn.bookstore.backend.dto.UpdateBookResponse;
import vn.bookstore.backend.model.Book;
import vn.bookstore.backend.model.Notification;
import vn.bookstore.backend.service.IBookService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("/sorted-and-paged")
    public ResponseEntity<Page<Book>> getAllBooksSortedAndPaged(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        Sort.Direction direction = (sortOrder.equalsIgnoreCase("asc")) ? Direction.ASC : Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveBook(@RequestBody BookCreateRequest book) {
//        bookService.saveBook(book);
        return ResponseEntity.ok(new Notification("hello"));
    }

    //
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
//        bookService.deleteBook(id);
//        return ResponseEntity.noContent().build();
//    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateBookResponse> updateBook(@PathVariable int id, @RequestBody UpdateBookResponse book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }
//
//    @GetMapping("/sorted-and-paged/by-categories")
//    public ResponseEntity<Page<Book>> getBooksByCategoriesAndPriceBetween(
//            @RequestParam List<BookCategory> categories,
//            @RequestParam(defaultValue = "0") int min,
//            @RequestParam(defaultValue = "0") int max,
//            @RequestParam(defaultValue = "id") String sortBy,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            @RequestParam(defaultValue = "asc") String sortOrder) {
//        Sort.Direction direction = (sortOrder.equalsIgnoreCase("asc")) ? Direction.ASC : Direction.DESC;
//        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
//        if (max == 0)
//            max = Integer.MAX_VALUE;
//        return ResponseEntity.ok(bookService.getBookByCategoriesAndPriceRange(categories, min, max, pageable));
//    }
//
//    @GetMapping("/sorted-and-paged/by-collection")
//    public ResponseEntity<Page<Book>> getBooksByCollectionAndPriceBetween(
//            @RequestParam(required = false) BookCollection collection,
//            @RequestParam(defaultValue = "0") int min,
//            @RequestParam(defaultValue = "0") int max,
//            @RequestParam(defaultValue = "id") String sortBy,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            @RequestParam(defaultValue = "asc") String sortOrder) {
//        Sort.Direction direction = (sortOrder.equalsIgnoreCase("asc")) ? Direction.ASC : Direction.DESC;
//        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
//        if (max == 0)
//            max = Integer.MAX_VALUE;
//        return ResponseEntity.ok(bookService.getBooksByCollectionAndPriceRanges(collection, min, max, pageable));
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<Page<Book>> searchBookByName(
//            @RequestParam String name,
//            @RequestParam(defaultValue = "id") String sortBy,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            @RequestParam(defaultValue = "asc") String sortOrder) {
//        Sort.Direction direction = (sortOrder.equalsIgnoreCase("asc")) ? Direction.ASC : Direction.DESC;
//        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
//        return ResponseEntity.ok(bookService.getBooksByName(name, pageable));
//    }
//
//    @GetMapping("")
//    public ResponseEntity<Page<Book>> searchPost(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) BookCollection collection,
//            @RequestParam(required = false) BookState state,
//            @RequestParam(defaultValue = "id") String sortBy,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            @RequestParam(defaultValue = "asc") String sortOrder) {
//        Sort.Direction direction = (sortOrder.equalsIgnoreCase("asc")) ? Direction.ASC : Direction.DESC;
//        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
//        return ResponseEntity.ok(bookService.queryBook(title, state, collection, pageable));
//    }
//
//    @GetMapping("/get-all")
//    public ResponseEntity<List<Book>> getAll() {
//        return ResponseEntity.ok(bookService.getAllBooks());
//    }
//
//    @GetMapping("/change-state/{id}")
//    public ResponseEntity<Void> changeBookState(@PathVariable Long id)
//    {
//        bookService.changeBookState(id);
//        return ResponseEntity.noContent().build();
//    }
}
