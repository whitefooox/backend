package web.controller.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/test")
public class TestService {
    
    @GET
    public Object test(){
        return "Test:" + "Hello ^_^";
    }
}
