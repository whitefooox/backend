package src.builder;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import src.model.api.in.IAuth;
import src.model.api.out.IUserRepository;

public class Builder {
    
    @Inject @Default
    private IAuth auth;

    @Inject @Default
    private IUserRepository userRepository;

    @Produces @Built
    public IAuth buildAuth(){
        auth.injectUserRepository(userRepository);
        return auth;
    }
}
