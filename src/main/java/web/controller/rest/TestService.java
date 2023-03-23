package web.controller.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import web.repository.anime.source.Jutsu;

@Path("/test")
public class TestService {
    
    @GET
    public Object test(){
        Jutsu jutsu = new Jutsu();
        return "Test:" + jutsu.parseDescribe("покемон");
    }
}
