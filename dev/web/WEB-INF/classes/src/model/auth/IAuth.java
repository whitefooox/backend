package src.model.auth;

import src.model.api.dto.User;
import src.model.api.out.IUserRepository;

public interface IAuth {
    boolean login(User user) throws Exception;
    String createToken(User user) throws Exception;
    boolean checkToken(User user, String token);
    void injectUserRepository(IUserRepository userRepository);
    boolean reg(User user);
}