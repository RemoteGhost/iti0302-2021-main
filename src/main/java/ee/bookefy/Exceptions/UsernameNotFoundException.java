package ee.bookefy.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException() {

    }
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
