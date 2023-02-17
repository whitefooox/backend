package web.model.watch;

import web.model.api.dto.Anime;

public interface IWatch {
    Anime search(String name);
    String source(String url, String userAgent);
}
