package ee.bookefy.configuration.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class JwtTokenProvider {

    private final JwtConfig jwtConfig;

    public String generateJwtToken(String subject) {
        long currentTimeMs = System.currentTimeMillis();
        String secret = jwtConfig.getSecret();
        int duration = jwtConfig.getDuration();
        return Jwts.builder().setSubject(subject)
                .addClaims(new HashMap<>())
                .setIssuedAt(new Date(currentTimeMs))
                .setExpiration(new Date(currentTimeMs + duration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean isValid(String token, String user) {
        return getUsernameFromToken(token).equals(user) && !hasExpired(token);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean hasExpired(String token) {
        Date date = Jwts.parser().setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody().getExpiration();
        return date.before(new Date());
    }
}
