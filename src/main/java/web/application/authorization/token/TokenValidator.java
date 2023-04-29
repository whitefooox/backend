package web.application.authorization.token;

import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class TokenValidator {
    
    private Key key;
    
    public TokenValidator(Key key){
        this.key = key;
    }

    public String validate(String token){
        Jws<Claims> claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);
        return claims.getBody().getSubject();
    }
}
