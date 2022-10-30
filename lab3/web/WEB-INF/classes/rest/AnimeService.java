package rest;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import model.auth.IAuth;
import model.data.entity.Anime;
import model.data.entity.User;
import model.watch.IWatch;
import model.watch.Watch;

@Path("/anime")
public class AnimeService {

    @Inject
    private IWatch watch;

    @Inject
    private IAuth auth;

    @GET
    @Path("/search/{name}")
    @Produces("application/json")
    public Response search(@PathParam("name") String name, @Context HttpHeaders headers){
        try {
            String token = headers.getRequestHeader("token").get(0);
            String login = headers.getRequestHeader("login").get(0);
            User user = new User();
            user.setLogin(login);
            if(!auth.checkToken(user, token)){
                return Response.status(Response.Status.UNAUTHORIZED).build(); 
            }
        } catch (Exception e) {
            return Response.serverError().build();
        }
        Anime anime = watch.search(name);
        Jsonb jsonb = JsonbBuilder.create();
        return Response.ok(jsonb.toJson(anime)).build();
    }

    @POST
    @Path("/download")
    @Consumes("application/json")
    @Produces("application/json")
    public Response download(String json, @Context HttpHeaders headers){
        try {
            String token = headers.getRequestHeader("token").get(0);
            String login = headers.getRequestHeader("login").get(0);
            User user = new User();
            user.setLogin(login);
            if(!auth.checkToken(user, token)){
                return Response.status(Response.Status.UNAUTHORIZED).build(); 
            }
        } catch (Exception e) {
            return Response.serverError().build();
        }
        String userAgent = headers.getRequestHeader("userAgent").get(0);
        Jsonb jsonb = JsonbBuilder.create();
        String url = jsonb.fromJson(json, String.class);
        String source = watch.download(url, userAgent);
        return Response.ok(jsonb.toJson(source)).build();
    }
}
