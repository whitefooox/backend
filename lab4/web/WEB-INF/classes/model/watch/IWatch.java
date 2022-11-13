package model.watch;

import model.data.entity.Anime;

public interface IWatch {
    Anime search(String name);
    String download(String url, String userAgent);
}
