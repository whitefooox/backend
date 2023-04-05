package web.model.auth;

public interface IAuth {
    boolean login(User user) throws Exception;
    String createToken(User user) throws Exception;
    boolean checkToken(User user, String token);
    void injectUserRepository(IUserRepository userRepository);
    boolean reg(User user);
}