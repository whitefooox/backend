package rest.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Token {
    
    public static String create(String login) throws NoSuchAlgorithmException{
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        login = login + "fedora";
        byte[] bytes = sha1.digest(login.getBytes());
        StringBuilder builder = new StringBuilder();
        for(byte b : bytes){
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static boolean check(String login, String token) throws NoSuchAlgorithmException{
        return create(login).equals(token);
    }
}