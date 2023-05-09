package web.infrastructure.builder;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import web.application.watch.anime.IAnimeRepository;
import web.application.watch.service.IWatch;

public class Builder {

    @Inject @Default
    private IAnimeRepository animeRepository;

    @Inject @Default
    private IWatch watchService;

    @Produces @Built
    public IWatch buildWatch(){
        watchService.setAnimeRepository(animeRepository);
        return watchService;
    }
}
