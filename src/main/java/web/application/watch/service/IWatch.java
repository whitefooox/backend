package web.application.watch.service;

import java.util.List;

import web.application.watch.anime.Anime;
import web.application.watch.anime.IAnimeRepository;

public interface IWatch {
    Anime search(String name);
    String source(String url, String userAgent);
    List<Anime> getAll();
    void updateAll();
    Anime getRandom();
    void setAnimeRepository(IAnimeRepository animeRepository);
}
