package rest.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import model.data.repository.IUserRepository;

@Path("/test")
public class TestService {
    
    @GET
    @Path("/")
    public Response test(){
        return Response.ok().build();
    }
}
