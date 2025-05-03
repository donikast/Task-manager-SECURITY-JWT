package bg.tu_varna.sit.taskmanager16042025.security;

import bg.tu_varna.sit.taskmanager16042025.exception.TaskApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTTokenProvider {

    @Value("${security.jwt.secret-key}")
    private String JWTSecret;

    @Value("${security.jwt.expiration-time}")
    private int JWTExpirationDate;

    //промяна
    public String generateToken(Authentication authentication) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWTExpirationDate);

        String token = Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }

    private SecretKey key() {
        byte[] keyBytes = Decoders.BASE64.decode(JWTSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsername(String token) {
        Claims claims =  Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public boolean validateToken(String token) throws TaskApiException {
        try {
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parse(token);
            return true;
        }
        catch (MalformedJwtException ex) {
            throw new TaskApiException("Invalid JWT token");
        }
        catch (ExpiredJwtException ex) {
            throw new TaskApiException("Expired JWT token");
        }
        catch (UnsupportedJwtException ex) {
            throw new TaskApiException("Unsupported JWT token");
        }
        catch (IllegalArgumentException ex) {
            throw new TaskApiException("JWT string claims is empty");
        }
    }
}
