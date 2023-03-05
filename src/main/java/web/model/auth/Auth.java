package web.model.auth;

import web.controller.token.Token;
import web.model.api.dto.User;
import web.model.api.out.IUserRepository;

public class Auth implements IAuth {

    private IUserRepository userRepository;

    @Override
    public boolean login(User user) throws Exception {
        return userRepository.check(user);
    }

    @Override
    public String createToken(User user) throws Exception {
        return Token.create(user.getLogin());
    }

    @Override
    public boolean checkToken(User user, String token){
        try {
            return Token.check(user.getLogin(), token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void injectUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean reg(User user) {
        return userRepository.add(user);
    }
}
