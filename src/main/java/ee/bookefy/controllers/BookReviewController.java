package ee.bookefy.controllers;

import ee.bookefy.models.Book;
import ee.bookefy.models.BookReview;
import ee.bookefy.models.User;
import ee.bookefy.service.BookReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class BookReviewController {

    private final BookReviewService bookReviewService;

    @GetMapping("")
    public List<BookReview> getAllBookReviews() {
        return bookReviewService.getBookReviewsForAllBooks();
    }

    @PostMapping("")
    public BookReview addReview(@RequestBody User user, Book book, String content) {
        return bookReviewService.createReview(user, book, content);
    }

}
