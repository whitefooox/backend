package web.model.api.dto;

import java.util.HashMap;

public class Anime {
    
    private String name;
    private String url;
    private String image;
    private HashMap<String, HashMap<String, String>> data;

    public Anime(String name, String url, String image){
        this.name = name;
        this.url = url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setData(HashMap<String, HashMap<String, String>> data) {
        this.data = data;
    }

    public HashMap<String, HashMap<String, String>> getData() {
        return data;
    }
}
