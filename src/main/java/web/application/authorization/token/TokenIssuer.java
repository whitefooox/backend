package web.application.authorization.token;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenIssuer {
    
    private Key key;

    public TokenIssuer(Key key){
        this.key = key;
    }

    public String issueToken(String login){
        Instant now = Instant.now();
        Instant expiryInstant = now.plus(Duration.ofMinutes(600L));
        String compactJws = Jwts.builder()
            .setSubject(login)
            .signWith(key, SignatureAlgorithm.HS256)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(expiryInstant))
            .compact();
        return compactJws;
    }
}
