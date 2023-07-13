package ee.bookefy.controllers;

import ee.bookefy.models.Book;
import ee.bookefy.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping()
    public List<Book> getAllBooksNoContent(){
        return bookService.getAllBooksNoContent();
    }
}
