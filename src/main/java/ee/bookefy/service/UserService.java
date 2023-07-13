package ee.bookefy.service;

import ee.bookefy.Exceptions.LoginRequestInvalidException;
import ee.bookefy.configuration.security.MyUserDetailsService;
import ee.bookefy.configuration.security.jwt.JwtTokenProvider;
import ee.bookefy.models.*;
import ee.bookefy.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
@Setter
public class UserService {

    private final AuthenticationManager authenticationManager;

    public final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getUsername().equals(" ")) {
            throw new LoginRequestInvalidException("invalid username");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().equals(" ")) {
            throw new LoginRequestInvalidException("invalid password");
        }
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails principal = (UserDetails) authenticate.getPrincipal();
        String token = jwtTokenProvider.generateJwtToken(principal.getUsername());
        LoginResponse response = new LoginResponse(principal.getUsername(), token, UserRole.USER);
        return response;
    }

    public void register(RegisterRequest registerRequest) {
        if (registerRequest.getUsername() == null || registerRequest.getUsername().equals(" ")) {
            throw new LoginRequestInvalidException("invalid username");
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().equals(" ")) {
            throw new LoginRequestInvalidException("invalid password");
        }
        if (userRepository.getUserByUsername(registerRequest.getUsername()) == null) {
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setRole(UserRole.USER);
            userRepository.save(user);
        } else {
            throw new LoginRequestInvalidException("username already taken");
        }

    }
}
