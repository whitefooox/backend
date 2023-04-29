package web.application.authorization.token;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Token {

    private static final TokenKey tokenKey = new TokenKey();
    private static final Key key = tokenKey.getKey();
    
    public static String create(String login){
        TokenIssuer tokenIssuer = new TokenIssuer(key);
        return tokenIssuer.issueToken(login);
    }

    public static boolean check(String login, String token) throws NoSuchAlgorithmException{
        TokenValidator tokenValidator = new TokenValidator(key);
        return login.equals(tokenValidator.validate(token));
    }
}