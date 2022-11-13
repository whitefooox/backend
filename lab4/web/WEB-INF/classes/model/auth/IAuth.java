package model.auth;

import model.data.entity.User;

public interface IAuth {
    boolean login(User user) throws Exception;
    String createToken(User user) throws Exception;
    boolean checkToken(User user, String token) throws Exception;
}