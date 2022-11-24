package src.repository.anime;

import java.util.List;

import src.model.api.dto.Anime;

public interface IAnimeRepository {
    
    List<Anime> getAll();
    Anime search(String name);
    String download(String url, String userAgent);
}
