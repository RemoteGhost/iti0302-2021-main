package ee.bookefy.models;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class BookNotesTest {
    User user1 = new User("Joh", "123456", UserRole.USER);
    Book book1 = new Book("Dracula", "Bram Stoker", "rest", "test");


    BookNotes emptyNote = new BookNotes(book1, user1, "");

    @Test
    void getId() {
        Long id = null;
        assertEquals(id, emptyNote.getId());
    }

    @Test
    void getBook() {
        assertEquals(book1, emptyNote.getBook());
    }

    @Test
    void getUser() {
        assertEquals(user1, emptyNote.getUser());
    }

    @Test
    void getNotes() {
        assertEquals("", emptyNote.getNotes());
    }

    @Test
    void setId() {
        emptyNote.setId(10L);
        assertEquals(10L, emptyNote.getId());
    }

    @Test
    void setBook() {
        assertEquals(book1, emptyNote.getBook());
        emptyNote.setBook(null);
        assertNull(emptyNote.getBook());
    }

    @Test
    void setUser() {
        assertEquals(user1, emptyNote.getUser());
        emptyNote.setUser(null);
        assertNull(emptyNote.getUser());
    }

    @Test
    void setNotes() {
        assertEquals("", emptyNote.getNotes());
        emptyNote.setNotes(null);
        assertNull(emptyNote.getNotes());
    }
}