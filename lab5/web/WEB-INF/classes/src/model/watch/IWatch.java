package src.model.watch;

import src.model.api.dto.Anime;

public interface IWatch {
    Anime search(String name);
    String download(String url, String userAgent);
}
