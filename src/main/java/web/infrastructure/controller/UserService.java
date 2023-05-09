package web.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import web.application.authorization.Authorizable;
import web.application.authorization.User;

@Path("/user")
public class UserService {

    @Inject
    private Authorizable authService;

    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(String dataJSON){
        Jsonb jsonb = JsonbBuilder.create();
        User user = jsonb.fromJson(dataJSON, User.class);
        Boolean isAuth = authService.auth(user.getLogin(), user.getPassword());
        if(isAuth){
            String token = authService.getToken(user.getLogin());
            return Response.ok(jsonb.toJson(token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/reg")
    @Consumes("application/json")
    @Produces("application/json")
    public Response reg(String dataJSON){
        Jsonb jsonb = JsonbBuilder.create();
        User user = jsonb.fromJson(dataJSON, User.class);
        Boolean isReg = authService.reg(user.getLogin(), user.getPassword(), user.getEmail());
        if(isReg){
            String token = authService.getToken(user.getLogin());
            return Response.ok(jsonb.toJson(token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
