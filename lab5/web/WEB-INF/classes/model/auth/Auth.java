package model.auth;

import java.security.NoSuchAlgorithmException;
import model.data.repository.IUserRepository;
import model.data.repository.UserRepositoryFactory;
import model.data.repository.UserRepositoryFactory.UserRepositoryType;
import rest.token.Token;

public class Auth implements IAuth {

    private IUserRepository userRepository;

    public Auth(){
        this.userRepository = new UserRepositoryFactory().createUserRepository(UserRepositoryType.SQL);
    }

    @Override
    public boolean login(String login, String password) throws Exception {
        return userRepository.check(login, password);
    }

    @Override
    public String createToken(String login) throws Exception {
        return Token.create(login);
    }

    @Override
    public boolean checkToken(String login, String token) throws NoSuchAlgorithmException {
        return Token.check(login, token);
    }
}
