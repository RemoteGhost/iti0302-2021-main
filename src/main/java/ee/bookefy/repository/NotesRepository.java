package ee.bookefy.repository;

import ee.bookefy.models.Book;
import ee.bookefy.models.BookNotes;
import ee.bookefy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<BookNotes, Long> {

    BookNotes getBookNotesByBookAndUser(Book book, User user);

    BookNotes getBookNotesById(Long id);

    Boolean existsBookNotesByBookAndUser(Book book, User user);
}
