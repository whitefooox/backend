package web.application.authorization.service;

import web.application.authorization.user.User;

public interface Tokenable {
    String createToken(User user);
    boolean checkToken(User user, String token);
}
