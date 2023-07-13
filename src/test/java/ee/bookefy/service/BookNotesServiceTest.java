package ee.bookefy.service;

import ee.bookefy.models.Book;
import ee.bookefy.models.BookNotes;
import ee.bookefy.models.User;
import ee.bookefy.models.UserRole;
import ee.bookefy.repository.BookRepository;
import ee.bookefy.configuration.security.jwt.JwtTokenProvider;
import ee.bookefy.repository.NotesRepository;
import ee.bookefy.repository.UserRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookNotesServiceTest {
    @MockBean
    private NotesRepository notesRepository;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private BookNotesService bookNotesService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    User user1 = new User("Joh", "123456", UserRole.USER);
    Book book1 = new Book("Dracula", "Bram Stoker", "test", "rest");

    String notes = "This is quite emotional, should share page 55 with mom";

//    @Ignore
//    @Test
//    void getBookNotesByBookAndUserReturnsNotes() {
//        String token = "asdfgh" + jwtTokenProvider.generateJwtToken("Joh") ;
//        String user = jwtTokenProvider.getUsernameFromToken(token);
//        System.out.println(user);
//        BookNotes bookNotes = new BookNotes(book1, user1, notes);
//        when(userRepository.getUserByUsername("Joh")).thenReturn(user1);
//        when(notesRepository.getBookNotesByBookAndUser(book1, user1)).thenReturn(bookNotes);
//        System.out.println(bookNotesService.getBookNotesByBookAndUser(token, user1.getId()).getNotes());
//        assertEquals(bookNotes.getNotes(), bookNotesService.getBookNotesByBookAndUser(token, user1.getId()).getNotes());
//    }

    @Test
    void getBookNotesByUserReturnsEmptyNotes(){
        String token = "asdfgh" + jwtTokenProvider.generateJwtToken("Joh") ;
        BookNotes emptyNote = new BookNotes(book1, user1, "");
        when(notesRepository.getBookNotesByBookAndUser(book1, user1)).thenReturn(emptyNote);
        System.out.println(bookNotesService.getBookNotesByBookAndUser(token, user1.getId()).getNotes());
        assertEquals(emptyNote.getNotes(), bookNotesService.getBookNotesByBookAndUser(token, user1.getId()).getNotes());
    }
}