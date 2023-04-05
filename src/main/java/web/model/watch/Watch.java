package web.model.watch;

import web.repository.anime.source.Jutsu;

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
        String result = animeRepository.getSource(url, userAgent);
        return result;
    }
}
