package ee.bookefy.service;

import ee.bookefy.models.Book;
import ee.bookefy.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
    Book book1 = new Book("Dracula", "Bram Stoker", "bla", "watevah");
    Book book2 = new Book("Moby", "Bram Stoker", "bla", "watevah");
    Book book3 = new Book("Bill", "Bram Stoker", "bla", "watevah");
    List<Book> bookList = List.of(book1, book2, book3);

    @Test
    void checkIfBookReturnsByTitle() {
        when(bookService.getBookById(1L)).thenReturn(book1);
        Book getBook = bookService.getBookById(1L);

        assertEquals(book1, getBook);
    }

    @Test
    void checkIfGetAllBooksNoContentReturnsAllBooksWithoutContent() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> booksWithoutContent = bookService.getAllBooksNoContent();
        for (Book book : booksWithoutContent) {
            assertThat(book.getContent()).isEqualTo(null);

        }
    }
}