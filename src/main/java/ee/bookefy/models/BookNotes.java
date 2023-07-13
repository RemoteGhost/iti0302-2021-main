package ee.bookefy.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class BookNotes {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;
    @Lob
    private String notes;

    public BookNotes(Book book, User user, String notes) {
        this.book = book;
        this.user = user;
        this.notes = notes;
    }

}
