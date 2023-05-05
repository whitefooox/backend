package web.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import web.application.watch.service.IWatch;
import web.infrastructure.builder.Built;

@Path("/test")
public class TestService {

    @Inject @Built
    IWatch watchService;
    
    @GET
    @Path("/anime/{name}")
    public Object test(@PathParam("name") String name){
        return watchService.search(name).getDescription();
    }
}
