package web.application.watch.anime;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Anime implements Serializable {
    
    private String name;
    private String url;
    private String image;
    private String description;
    private LinkedHashMap<String, LinkedHashMap<String, String>> data;

    public Anime(){}

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

    public void setData(LinkedHashMap<String, LinkedHashMap<String, String>> data) {
        this.data = data;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getData() {
        return data;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
