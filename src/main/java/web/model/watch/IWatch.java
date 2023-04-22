package web.model.watch;

import java.util.List;

public interface IWatch {
    Anime search(String name);
    String source(String url, String userAgent);
    List<Anime> getAll();
    void updateAll();
    Anime getRandom();
}
