package ee.bookefy.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LoginRequestInvalidException extends RuntimeException {

    public LoginRequestInvalidException() {

    }
    public LoginRequestInvalidException(String message) {
        super(message);
    }
}
