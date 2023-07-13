package ee.bookefy.service;

import ee.bookefy.models.Book;
import ee.bookefy.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Setter
public class BookService {
    private final BookRepository bookRepository;

    public Book getBookById(Long id) {
        return bookRepository.getBookById(id);
    }


    public List<Book> getAllBooksNoContent() {
        List<Book> list = new ArrayList<>();
        List<Book> allBooks = bookRepository.findAll();
        for (Book book : allBooks) {
            Book books = new Book();
            books.setId(book.getId());
            books.setTitle(book.getTitle());
            books.setAuthor(book.getAuthor());
            books.setCoverUrl(book.getCoverUrl());
            list.add(books);
        }
        return list;


    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }


}
