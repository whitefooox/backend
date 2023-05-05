package web.application.authorization.service;

import web.application.authorization.token.Token;
import web.application.authorization.user.IUserRepository;
import web.application.authorization.user.User;

public class Auth implements Authorizable, Tokenable {

    private IUserRepository userRepository;

    @Override
    public boolean login(User user) throws Exception {
        return userRepository.check(user);
    }

    @Override
    public String createToken(User user){
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
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(User user) {
        return userRepository.add(user);
    }
}
