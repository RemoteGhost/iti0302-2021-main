package ee.bookefy.service;

import ee.bookefy.models.Book;
import ee.bookefy.models.BookReview;
import ee.bookefy.models.User;
import ee.bookefy.models.UserRole;
import ee.bookefy.repository.BookRepository;
import ee.bookefy.repository.BookReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class BookReviewServiceTest {

    @MockBean
    private BookReviewRepository bookReviewRepository;
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookReviewService bookReviewService;

    User user1 = new User("Joh", "123456", UserRole.USER);
    Book book1 = new Book("Dracula", "Bram Stoker", "test", "rest");

    String content = "This is such a great book, one of the classics, must-read!";

    @Test
    void checkIfCreateReviewWorks() {
        BookReview bookReview = bookReviewService.createReview(user1, book1, content);
        when(bookReviewRepository.getAllByBook(book1)).thenReturn(List.of(bookReview));
        assertEquals(List.of(bookReview), bookReviewService.getBookReviewRepository().getAllByBook(book1));
    }

    @Test
    void getBookReviewsForAllBooksReturnsListOfAllBookReviews() {
        BookReview bookReview = bookReviewService.createReview(user1, book1, content);
        when(bookRepository.findAll()).thenReturn(List.of(book1));
        when(bookReviewRepository.getAllByBook(book1)).thenReturn(List.of(bookReview));
        assertEquals(List.of(bookReview), bookReviewService.getBookReviewsForAllBooks());
    }
}