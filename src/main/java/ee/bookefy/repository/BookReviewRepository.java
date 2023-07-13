package ee.bookefy.repository;

import ee.bookefy.models.Book;
import ee.bookefy.models.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long> {

    List<BookReview> getAllByBook(Book book);
}
