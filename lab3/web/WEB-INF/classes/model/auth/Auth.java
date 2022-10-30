package model.auth;

import java.security.NoSuchAlgorithmException;

import model.data.entity.User;
import model.data.repository.IUserRepository;
import model.db.SqlUserRepository;
import model.token.Token;

public class Auth implements IAuth {

    private IUserRepository userRepository;

    public Auth(){
        this.userRepository = new SqlUserRepository();
    }

    @Override
    public boolean login(User user) throws Exception {
        return userRepository.check(user);
    }

    @Override
    public String createToken(User user) throws Exception {
        return Token.create(user.getLogin());
    }

    @Override
    public boolean checkToken(User user, String token) throws NoSuchAlgorithmException {
        return Token.check(user.getLogin(), token);
    }
}
