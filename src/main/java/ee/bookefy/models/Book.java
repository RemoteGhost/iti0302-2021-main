package ee.bookefy.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Book{
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String author;
    @Lob
    private String content;
    private String coverUrl;

    public Book(String title, String author, String content, String coverurl) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.coverUrl = coverurl;
    }

}