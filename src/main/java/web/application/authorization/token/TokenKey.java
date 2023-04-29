package web.application.authorization.token;

import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class TokenKey {
    
    private Key key;

    public TokenKey(){
        final String SECRET_KEY = "kRY3jkqUKJvc9XEIpiE7mUdJn1ABBbarvEMSgC9Bu30EnpO4W8z1BhFRRQySUMBW8" +
        "CgA8Aq0Nss74BiUMq54aH2TumAy0C0tMkKG045EhRhvscYoZDXAo2mvdwlPkWk0JVKduy2NdufbL7CE8ZvmhWZRm" +
        "2vG5BlqWEZ9Yo61RPUWvt3pvVcguaWeNW9cgyfJU6JmZzBMPPN6JJD9oeLyYbUqTCj9W6kFFWussbtB4XLlDk8nxWjR4Bfwv8uUtzFj";
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        key = new SecretKeySpec(decodedKey, "HmacSHA256");
    }

    public Key getKey(){
        return key;
    }
}
