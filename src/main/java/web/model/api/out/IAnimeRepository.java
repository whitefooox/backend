package web.model.api.out;

import java.util.List;
import web.model.api.dto.Anime;

public interface IAnimeRepository {
    Anime search(String name);
    String getSource(String url, String userAgent);
}
