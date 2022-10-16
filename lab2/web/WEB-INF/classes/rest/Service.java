package rest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import model.task.Task;
import model.task.TaskDAO;
import model.token.Token;
import model.user.User;
import model.user.UserDAO;

@Path("/")
public class Service {
    
    @Path("/test")
    @GET
    @Produces("application/json")
    public Response test(){
        Jsonb jsonb = JsonbBuilder.create();
        String resultJSON = jsonb.toJson(new String("test"));
        return Response.ok(resultJSON).build();
    }

    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public Response auth(String dataJSON){
        Jsonb jsonb = JsonbBuilder.create();
        User user = jsonb.fromJson(dataJSON, User.class);
        try {
            if (UserDAO.validate(user.getLogin(), user.getPassword())){
                String token = Token.create(user.getLogin());
                String result = "{\"token\":\"" + token + "\",\"login\":\"" + user.getLogin() + "\"}";
                return Response.ok(result).build();
            }
            else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.ok().build();
        }
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(String dataJSON, @Context HttpHeaders headers) throws NoSuchAlgorithmException {
        String token = headers.getRequestHeader("Token").get(0);
        String login = headers.getRequestHeader("Login").get(0);
        if (!Token.check(login, token)){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Jsonb jsonb = JsonbBuilder.create();
        Task task = jsonb.fromJson(dataJSON, Task.class);
        try {
            task.setUserId(UserDAO.getId(login));
            TaskDAO.insert(task);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }    
    }

    @POST
    @Path("/tasks")
    @Produces("application/json")
    public Response tasks(@Context HttpHeaders headers) throws NoSuchAlgorithmException{
        String token = headers.getRequestHeader("Token").get(0);
        String login = headers.getRequestHeader("Login").get(0);
        if (!Token.check(login, token)){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Jsonb jsonb = JsonbBuilder.create();
        List<Task> tasks;
        try {
            tasks = TaskDAO.getAll(UserDAO.getId(login));
            String result = jsonb.toJson(tasks);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/state")
    @Consumes("application/json")
    @Produces("application/json")
    public Response setState(String dataJSON, @Context HttpHeaders headers) throws NoSuchAlgorithmException{
        String token = headers.getRequestHeader("Token").get(0);
        String login = headers.getRequestHeader("Login").get(0);
        if (!Token.check(login, token)){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Jsonb jsonb = JsonbBuilder.create();
        Task task = jsonb.fromJson(dataJSON, Task.class);
        try {
            TaskDAO.updateState(task.getId(), task.getStatus());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }  
    }

    @POST
    @Path("/delete")
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(String dataJSON, @Context HttpHeaders headers) throws NoSuchAlgorithmException{
        String token = headers.getRequestHeader("Token").get(0);
        String login = headers.getRequestHeader("Login").get(0);
        if (!Token.check(login, token)){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Jsonb jsonb = JsonbBuilder.create();
        Task task = jsonb.fromJson(dataJSON, Task.class);
        try {
            TaskDAO.delete(task.getId());
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }  
    }
}
