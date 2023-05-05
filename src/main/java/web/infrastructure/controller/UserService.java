package web.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import web.application.authorization.service.Authorizable;
import web.application.authorization.service.Tokenable;
import web.application.authorization.user.User;
import web.infrastructure.builder.Built;

@Path("/user")
public class UserService {

    @Inject @Built
    private Authorizable authService;

    @Inject
    private Tokenable tokenService;
    
    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(String dataJSON){
        Jsonb jsonb = JsonbBuilder.create();
        User user = jsonb.fromJson(dataJSON, User.class);
        try {
            if (authService.login(user)){
                return Response.ok(jsonb.toJson(tokenService.createToken(user))).build();
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
            if (authService.register(user)){
                return Response.ok(jsonb.toJson(tokenService.createToken(user))).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
