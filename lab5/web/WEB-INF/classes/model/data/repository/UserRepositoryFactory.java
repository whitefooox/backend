package model.data.repository;

import jakarta.inject.Inject;

public class UserRepositoryFactory {

    @Inject
    IUserRepository sqlUserRepository;

    public enum UserRepositoryType {
        SQL
    }
    
    public IUserRepository createUserRepository(UserRepositoryType type){
        IUserRepository userRepository = null;
        switch (type) {
            case SQL:
                userRepository = sqlUserRepository;
                break;
            default:
                break;
        }
        return userRepository;
    }
}
