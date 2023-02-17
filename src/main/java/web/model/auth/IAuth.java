package web.model.auth;

import web.model.api.dto.User;
import web.model.api.out.IUserRepository;

public interface IAuth {
    boolean login(User user) throws Exception;
    String createToken(User user) throws Exception;
    boolean checkToken(User user, String token);
    void injectUserRepository(IUserRepository userRepository);
    boolean reg(User user);
}