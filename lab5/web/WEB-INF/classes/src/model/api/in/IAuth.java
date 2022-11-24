package src.model.api.in;

import src.model.api.out.IUserRepository;

public interface IAuth {
    boolean login(String login, String password) throws Exception;
    String createToken(String login) throws Exception;
    boolean checkToken(String login, String token);
    void injectUserRepository(IUserRepository userRepository);
}