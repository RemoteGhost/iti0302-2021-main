package ee.bookefy.service;

import ee.bookefy.models.Book;
import ee.bookefy.models.BookReview;
import ee.bookefy.models.User;
import ee.bookefy.repository.BookRepository;
import ee.bookefy.repository.BookReviewRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Setter
public class BookReviewService {
    private final BookReviewRepository bookReviewRepository;
    private final BookRepository bookRepository;

    public BookReview createReview(User user, Book book, String content) {
        BookReview bookReview = new BookReview(user, book, content);
        bookReviewRepository.save(bookReview);
        return bookReview;
    }

    public BookReviewRepository getBookReviewRepository() {
        return bookReviewRepository;
    }

    public List<BookReview> getBookReviewsForAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookReview> bookReviews = new ArrayList<>();
        for (Book book: books) {
            List<BookReview> reviews = bookReviewRepository.getAllByBook(book);
            bookReviews.addAll(reviews);
        }
        return bookReviews;
    }

}
