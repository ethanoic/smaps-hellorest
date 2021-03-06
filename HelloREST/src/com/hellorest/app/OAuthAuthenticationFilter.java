package com.hellorest.app;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
 
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
 
import org.glassfish.jersey.internal.util.Base64;

import io.jsonwebtoken.Claims;
 
/**
 * This filter verify the access permissions for a user
 * based on username and passowrd provided in request
 * */
@Provider
public class OAuthAuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
    @Context
    private ResourceInfo resourceInfo;
     
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Bearer";;
      
    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if( ! method.isAnnotationPresent(PermitAll.class))
        {
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class))
            {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("This resource denies all").build());
                return;
            }
              
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
              
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
              
            //If no authorization information present; block access
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("no authorization header").build());
                return;
            }
              
            final String jwt = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            if( !JWTToken.validateJWT(jwt))
            {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Token Invalid").build());
                return;
            }

            //Verify user access
            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                // get role from claims
                Claims claims = JWTToken.getClaims(jwt);
                String userRole = claims.get("role").toString();
                System.out.println("role authenticated is " + userRole);
                System.out.println("method roles are " + rolesAnnotation.toString());
        		if (!rolesSet.contains(userRole)) 
        		{
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                            .entity("no access due to roles").build());
                    return;
        		}
            }
        }
    }
}