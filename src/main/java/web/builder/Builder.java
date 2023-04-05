package web.builder;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import web.model.auth.IAuth;
import web.model.auth.IUserRepository;

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
