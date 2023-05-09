package web.infrastructure.interceptor;

import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import web.application.authorization.Authorizable;

@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Inject
    private Authorizable auth;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String login = requestContext.getHeaderString("login");
        String token = requestContext.getHeaderString("token");
        if (!auth.check(login, token)) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
    } 
}
