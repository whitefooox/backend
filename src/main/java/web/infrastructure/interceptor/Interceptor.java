package web.infrastructure.interceptor;

import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import web.application.authorization.service.Tokenable;
import web.application.authorization.user.User;

@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Inject
    private Tokenable tokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String login = requestContext.getHeaderString("login");
        String token = requestContext.getHeaderString("token");
        User user = new User();
        user.setLogin(login);
        if (!tokenService.checkToken(user, token)) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
    } 
}
