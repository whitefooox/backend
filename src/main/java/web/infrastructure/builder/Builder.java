package web.infrastructure.builder;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import web.application.chat.Chatable;
import web.application.chat.Sendable;
import web.application.watch.anime.IAnimeRepository;
import web.application.watch.service.IWatch;

public class Builder {

    @Inject @Default
    private IAnimeRepository animeRepository;

    @Inject @Default
    private IWatch watchService;

    @Inject @Default
    private Sendable sender;

    @Inject @Default
    private Chatable chat;

    @Produces @Built
    public IWatch buildWatch(){
        watchService.setAnimeRepository(animeRepository);
        return watchService;
    }

    @Produces @Built
    public Chatable buildChatable(){
        chat.setSender(sender);
        return chat;
    }
}
