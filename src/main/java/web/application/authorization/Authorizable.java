package web.application.authorization;

public interface Authorizable {
    boolean auth(String login, String password);
    boolean reg(String login, String password, String email);
    boolean check(String login, String token);
    String getToken(String login);
}
