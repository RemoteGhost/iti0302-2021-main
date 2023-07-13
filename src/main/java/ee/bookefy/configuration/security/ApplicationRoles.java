package ee.bookefy.configuration.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationRoles {
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
}

