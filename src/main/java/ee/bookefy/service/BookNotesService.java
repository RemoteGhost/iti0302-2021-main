package ee.bookefy.service;

import ee.bookefy.configuration.security.jwt.JwtTokenProvider;
import ee.bookefy.models.Book;
import ee.bookefy.models.BookNotes;
import ee.bookefy.models.User;
import ee.bookefy.repository.BookRepository;
import ee.bookefy.repository.NotesRepository;
import ee.bookefy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookNotesService {
    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public BookNotes getBookNotesByBookAndUser(String token, Long id) {
        Book book = bookRepository.getBookById(id);
        String userName = jwtTokenProvider.getUsernameFromToken(token.substring(6));
        User user = userRepository.getUserByUsername(userName);
        if(notesRepository.existsBookNotesByBookAndUser(book, user)) {
            return notesRepository.getBookNotesByBookAndUser(book, user);
        } else {
            BookNotes emptyNote = new BookNotes(book, user, "");
            notesRepository.save(emptyNote);
            return emptyNote;
        }
    }

    public BookNotes updateBookNotes(BookNotes notes) {
        BookNotes note = notesRepository.getBookNotesById(notes.getId());
        note.setNotes(notes.getNotes());
        notesRepository.save(note);
        return notesRepository.getBookNotesById(notes.getId());

    }
}
