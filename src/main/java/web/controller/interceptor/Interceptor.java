package web.controller.interceptor;

import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import web.model.auth.IAuth;
import web.model.auth.User;

@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Inject
    private IAuth auth;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String login = requestContext.getHeaderString("login");
        String token = requestContext.getHeaderString("token");
        User user = new User();
        user.setLogin(login);
        if (!auth.checkToken(user, token)) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
    } 
}
