package ee.bookefy.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginResponse {
    private String username;
    private String token;
    private UserRole role;

    public LoginResponse(String username, String token, UserRole user) {
        this.username = username;
        this.token = token;
        this.role = user;
    }
}
