package ee.bookefy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class BookefyApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(BookefyApplication.class, args);
	}

}
