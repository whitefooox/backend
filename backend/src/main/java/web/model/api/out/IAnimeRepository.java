package web.model.api.out;

import java.util.List;
import web.model.api.dto.Anime;

public interface IAnimeRepository {
    
    List<Anime> getAll();
    Anime search(String name);
    String download(String url, String userAgent);
}
