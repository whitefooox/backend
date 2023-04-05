package web.model.watch;

public interface IAnimeRepository {
    Anime search(String name);
    String getSource(String url, String userAgent);
}
