package web.infrastructure.builder;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import web.application.authorization.service.IAuth;
import web.application.authorization.user.IUserRepository;
import web.application.watch.anime.IAnimeRepository;
import web.application.watch.service.IWatch;

public class Builder {
    
    @Inject @Default
    private IAuth auth;

    @Inject @Default
    private IUserRepository userRepository;

    @Inject @Default
    private IAnimeRepository animeRepository;

    @Inject @Default
    private IWatch watch;


    @Produces @Built
    public IAuth buildAuth(){
        auth.setUserRepository(userRepository);
        return auth;
    }

    @Produces @Built
    public IWatch buildWatch(){
        watch.setAnimeRepository(animeRepository);
        return watch;
    }
}
