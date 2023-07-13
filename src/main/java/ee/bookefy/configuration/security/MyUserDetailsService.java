package ee.bookefy.configuration.security;

import ee.bookefy.models.User;
import ee.bookefy.models.UserRole;
import ee.bookefy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), toAuthorities(user));
    }

    private List<SimpleGrantedAuthority> toAuthorities(User user) {
        return getRoles(user.getRole())
                .map(UserRole::toApplicationRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    private Stream<UserRole> getRoles(UserRole role) {
            if (role.isAdmin()) {
                return Arrays.stream(UserRole.values());
            }
            return Stream.of(role);
        }
}

