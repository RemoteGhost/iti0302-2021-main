package ee.bookefy.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class BookReview {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
    private String content;
    @ElementCollection
    private List<Long> likes;

    public BookReview(User user, Book book, String content) {
        this.user = user;
        this.book = book;
        this.content = content;
        this.likes = new ArrayList<Long>(Arrays.asList(user.getId()));

    }
    private boolean hasUserVoted(User user){
        return likes.contains(user.getId());
    }

    private int getAmmoutOfLikes(){
        return likes.size();
    }

    private boolean vote(User user) {
        if (hasUserVoted(user)) {
            return false;
        }
        else {
            likes.add(user.getId());
            return true;
        }

    }

}
