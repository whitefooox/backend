package rest.service;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import model.auth.IAuth;
import model.data.entity.User;

@Path("/user")
public class UserService {

    @Inject
    private IAuth auth;
    
    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(String dataJSON){
        Jsonb jsonb = JsonbBuilder.create();
        User user = jsonb.fromJson(dataJSON, User.class);
        try {
            if (auth.login(user.getLogin(), user.getPassword())){
                return Response.ok(jsonb.toJson(auth.createToken(user.getLogin()))).build();
            }
            else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
