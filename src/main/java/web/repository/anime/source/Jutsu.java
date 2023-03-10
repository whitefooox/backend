package web.repository.anime.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import web.model.api.dto.Anime;
import web.model.api.out.IAnimeRepository;

public class Jutsu implements IAnimeRepository {
    
    private String url = "https://jut.su";
    private List<String> userAgents;

    public Jutsu(){
        this.userAgents = new ArrayList<>();
        this.userAgents.add("Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0");
        this.userAgents.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        this.userAgents.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/91.0 Waterfox/91.10.0");
    }

    private String getUserAgent(){
        Random rand = new Random();
        return this.userAgents.get(rand.nextInt(userAgents.size()));
    }

    @Override
    public List<Anime> getAll() {
        Document document;
        List<Anime>  allAnime;
        try {
            document = Jsoup.connect(this.url + "/" + "anime").userAgent(this.getUserAgent()).get();
            allAnime = new ArrayList<>();
            Elements animes = document.select("div.all_anime_global");
            for (Element anime : animes) {
                String name = anime.select("div.aaname").first().text();
                String url = this.url + anime.select("a").first().attr("href");
                allAnime.add(new Anime(name, url, null));
            }
            while (!document.select("a.vnright").isEmpty()){
                String urlNext = this.url + document.select("a.vnright").first().attr("href");
                System.out.println(urlNext);
                document = Jsoup.connect(urlNext).userAgent(this.getUserAgent()).get();
                animes = document.select("div.all_anime_global");
                for (Element anime : animes) {
                    String name = anime.select("div.aaname").first().text();
                    String url = this.url + anime.select("a").first().attr("href");
                    allAnime.add(new Anime(name, url, null));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return allAnime;
    }

    @Override
    public Anime search(String name){
        Document document;
        try {
            document = Jsoup.connect(this.url + "/" + name).userAgent(this.getUserAgent()).get();
            if(document.select("h1.header_video").isEmpty()){
                return null;
            }
            String nameTarget = document.select("h1.header_video").first().text();
            String url = document.location();
            String style = document.select("div.all_anime_title").attr("style");
            String image = style.substring(style.indexOf("'") + 1, style.lastIndexOf("'"));
            Anime anime = new Anime(nameTarget, url, image);
            anime.setData(getData(url));
            return anime;
        } catch (IOException e) {
            return null;
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getData(String url){
        Document document = null;
        try {
            document = Jsoup.connect(url).userAgent(this.getUserAgent()).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if(document.select("h1.header_video").isEmpty()){
            return null;
        }
        LinkedHashMap<String, LinkedHashMap<String, String>> data = new LinkedHashMap<>();
        for (Element elem : document.select("a.short-btn")) {
            String[] fullName = elem.text().split(" ");
            for(int i = 0; i < fullName.length - 1; i++){
                String word = fullName[i];
                String nextWord = fullName[i + 1];
                if(isNumeric(word)){
                    switch (nextWord) {
                        case "сезон": {
                            String season = word + " " + nextWord;
                            if(data.get(season) == null){
                                data.put(season, new LinkedHashMap<>());
                            }
                            String seria = fullName[i + 2] + " " + fullName[i + 3];
                            data.get(season).put(seria, this.url + elem.attr("href"));
                            break;
                        }
                        case "фильм": {
                            if(data.get("Фильмы") == null){
                                data.put("Фильмы", new LinkedHashMap<>());
                            }
                            String seria = word + " " + nextWord;
                            data.get("Фильмы").put(seria, this.url + elem.attr("href"));
                            break;
                        }
                        case "серия": {
                            if(data.get("1 сезон") == null){
                                data.put("1 сезон", new LinkedHashMap<>());
                            }
                            String seria = word + " " + nextWord;
                            data.get("1 сезон").put(seria, this.url + elem.attr("href"));
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
        }
        return data;
    }

    public String download(String url, String userAgent){
        Document document = null;
        try {
            document = Jsoup.connect(url).userAgent(userAgent).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Element video = document.select("div.videoContent").select("source").first();
        return video.attr("src");
    }
}
