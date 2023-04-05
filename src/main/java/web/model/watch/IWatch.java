package web.model.watch;

public interface IWatch {
    Anime search(String name);
    String source(String url, String userAgent);
}
