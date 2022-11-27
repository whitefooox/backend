package src.controller.rest;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import src.builder.Built;
import src.model.api.dto.User;
import src.model.auth.IAuth;

@Path("/user")
public class UserService {

    @Inject @Built
    private IAuth auth;
    
    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(String dataJSON){
        Jsonb jsonb = JsonbBuilder.create();
        User user = jsonb.fromJson(dataJSON, User.class);
        try {
            if (auth.login(user)){
                return Response.ok(jsonb.toJson(auth.createToken(user))).build();
            }
            else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/reg")
    @Consumes("application/json")
    @Produces("application/json")
    public Response reg(String dataJSON){
        Jsonb jsonb = JsonbBuilder.create();
        User user = jsonb.fromJson(dataJSON, User.class);
        try {
            if (auth.reg(user)){
                return Response.ok(jsonb.toJson(auth.createToken(user))).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
