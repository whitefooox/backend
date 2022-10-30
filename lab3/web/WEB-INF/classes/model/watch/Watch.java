package model.watch;

import model.data.entity.Anime;
import model.data.repository.IAnimeRepository;
import model.source.jutsu.Jutsu;

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
    public String download(String url, String userAgent) {
        String result = animeRepository.download(url, userAgent);
        return result;
    }
}
