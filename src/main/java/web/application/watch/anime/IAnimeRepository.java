package web.application.watch.anime;

import java.util.List;

public interface IAnimeRepository {
    Anime search(String name);
    String getSource(String url, String userAgent);
    List<Anime> getAll();
    void setAll(List<Anime> animeList);
    Anime getRandom();
}
