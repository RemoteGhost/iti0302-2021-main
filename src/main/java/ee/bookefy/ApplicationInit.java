package ee.bookefy;

import ee.bookefy.models.Book;
import ee.bookefy.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@AllArgsConstructor
public class ApplicationInit implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        String draculaContent = loadResource("classpath:data/Dracula, by Bram Stoker.html");
        String mobyContent = loadResource("classpath:data/Moby Dick; Or the Whale, by Herman Melville.html");
        String pictureContent = loadResource("classpath:data/The Picture of Dorian Gray, by Oscar Wilde.html");
        String hamletContent = loadResource("classpath:data/Hamlet, by William Shakespeare.html");
        Book moby = new Book("Moby Dick", "Herman Melville", mobyContent, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1327940656l/153747.jpg");
        Book dracula = new Book("Dracula", "Bram Stoker", draculaContent, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1387151694l/17245.jpg");
        Book picture = new Book("The Picture of Dorian Gray", "Oscar Wilde", pictureContent, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1546103428l/5297.jpg");
        Book hamlet = new Book("Hamlet", "William Shakespeare", hamletContent, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1351051208l/1420.jpg");
        bookRepository.save(moby);
        bookRepository.save(dracula);
        bookRepository.save(picture);
        bookRepository.save(hamlet);
    }

    private String loadResource(String location) {
        Resource resource = resourceLoader.getResource(location);
        return asString(resource);
    }

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
