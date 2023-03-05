package web.controller.token;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Token {
    
    public static String create(String login){
        TokenKey tokenKey = new TokenKey();
        Key key = tokenKey.getKey();
        TokenIssuer tokenIssuer = new TokenIssuer(key);
        String jwt = tokenIssuer.issueToken(login);
        return jwt;
    }

    public static boolean check(String login, String token) throws NoSuchAlgorithmException{
        TokenKey tokenKey = new TokenKey();
        Key key = tokenKey.getKey();
        TokenValidator tokenValidator = new TokenValidator(key);
        String loginValidate = tokenValidator.validate(token);
        return login.equals(loginValidate);
    }
}