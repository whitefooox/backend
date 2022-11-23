package model.auth;

public interface IAuth {
    boolean login(String login, String password) throws Exception;
    String createToken(String login) throws Exception;
    boolean checkToken(String login, String token) throws Exception;
}