package web.model.watch;

import java.util.List;

import web.repository.anime.source.Jutsu;

public class Watch implements IWatch {

    private IAnimeRepository animeRepository;

    public Watch(){
        this.animeRepository = new Jutsu();
    }

    @Override
    public Anime search(String name) {
        Anime anime = animeRepository.search(name);
        return anime;
    }

    @Override
    public String source(String url, String userAgent) {
        String result = animeRepository.getSource(url, userAgent);
        return result;
    }

    @Override
    public List<Anime> getAll() {
        return animeRepository.getAll();
    }

    @Override
    public void updateAll() {
        animeRepository.setAll(animeRepository.getAll());
    }

    @Override
    public Anime getRandom(){
        return animeRepository.getRandom();
    }
}
