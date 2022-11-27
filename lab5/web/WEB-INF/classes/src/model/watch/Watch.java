package src.model.watch;

import src.model.api.dto.Anime;
import src.repository.anime.IAnimeRepository;
import src.repository.anime.source.Jutsu;

public class Watch implements IWatch {

    private IAnimeRepository animeRepository;

    public Watch(){
        this.animeRepository = new Jutsu();
    }

    @Override
    public Anime search(String name) {
        Anime anime = animeRepository.search(name);
        return anime;
    }

    @Override
    public String source(String url, String userAgent) {
        String result = animeRepository.download(url, userAgent);
        return result;
    }
}
