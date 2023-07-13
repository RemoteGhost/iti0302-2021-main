package ee.bookefy;

import ee.bookefy.models.User;
import ee.bookefy.models.UserRole;
import ee.bookefy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsersInit implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        String pass1 = passwordEncoder.encode("ENCRYPTME");
        String pass2 = passwordEncoder.encode("ENCRYPTURMOM");
        String pass3 = passwordEncoder.encode("XxXP...ySlayerXxX");
        String pass4 = passwordEncoder.encode("admin");
        User user1 = new User("GlebLovesPizza123", pass1, UserRole.USER);
        User user2 = new User("namingIsHard", pass2, UserRole.USER);
        User user3 = new User("IAmANiceUsernameForTestsButImWayTooLong", pass3, UserRole.USER);
        User user4 = new User("admin", pass4, UserRole.ADMIN);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
    }


}
