package web.infrastructure.controller.rest;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import web.application.watch.anime.Anime;
import web.application.watch.service.IWatch;
import web.infrastructure.builder.Built;
import web.infrastructure.interceptor.TokenRequired;

@Path("/anime")
public class AnimeService {

    @Inject @Built
    private IWatch watch;

    @GET
    @TokenRequired
    @Path("/search/{name}")
    @Produces("application/json")
    public Response search(@PathParam("name") String name, @HeaderParam("login") String login){
        Anime anime = watch.search(name);
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(anime);
        return Response.ok(json).build();
    }

    @POST
    @TokenRequired
    @Path("/source")
    @Consumes("application/json")
    @Produces("application/json")
    public Response source(String json, @Context HttpHeaders headers){
        String userAgent = headers.getRequestHeader("userAgent").get(0);
        Jsonb jsonb = JsonbBuilder.create();
        String url = jsonb.fromJson(json, String.class);
        String source = watch.source(url, userAgent);
        return Response.ok(jsonb.toJson(source)).build();
    }
}
