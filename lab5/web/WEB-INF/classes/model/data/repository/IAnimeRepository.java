package model.data.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import model.data.entity.Anime;

public interface IAnimeRepository {
    
    List<Anime> getAll();
    Anime search(String name);
    String download(String url, String userAgent);
}
