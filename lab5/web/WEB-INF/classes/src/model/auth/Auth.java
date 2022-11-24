package src.model.auth;

import src.model.api.in.IAuth;
import src.model.api.out.IUserRepository;
import src.model.auth.token.Token;

public class Auth implements IAuth {

    private IUserRepository userRepository;

    @Override
    public boolean login(String login, String password) throws Exception {
        return userRepository.check(login, password);
    }

    @Override
    public String createToken(String login) throws Exception {
        return Token.create(login);
    }

    @Override
    public boolean checkToken(String login, String token){
        try {
            return Token.check(login, token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void injectUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
