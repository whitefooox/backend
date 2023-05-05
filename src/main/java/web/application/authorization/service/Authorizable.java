package web.application.authorization.service;

import web.application.authorization.user.IUserRepository;
import web.application.authorization.user.User;

public interface Authorizable {
    boolean login(User user) throws Exception;
    boolean register(User user);
    void setUserRepository(IUserRepository userRepository);
}
