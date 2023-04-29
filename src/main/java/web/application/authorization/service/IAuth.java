package web.application.authorization.service;

import web.application.authorization.user.IUserRepository;
import web.application.authorization.user.User;

public interface IAuth {

    boolean login(User user) throws Exception;
    boolean register(User user);

    String createToken(User user) throws Exception;
    boolean checkToken(User user, String token);
    
    void setUserRepository(IUserRepository userRepository);
}