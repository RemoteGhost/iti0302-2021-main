package ee.bookefy.controllers;

import ee.bookefy.models.BookNotes;
import ee.bookefy.service.BookNotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static ee.bookefy.configuration.security.ApplicationRoles.USER;



@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class BookNotesController {
    private final BookNotesService bookNotesService;

    @Secured(USER)
    @GetMapping("/{id}")
    public BookNotes getNotesByBookAndUser(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        BookNotes notes = bookNotesService.getBookNotesByBookAndUser(token, id);
        return notes;
    }
    @Secured(USER)
    @PutMapping("/save")
    public BookNotes updateNotes(@RequestBody BookNotes bookNotes) {
        return bookNotesService.updateBookNotes(bookNotes);
    }
}
