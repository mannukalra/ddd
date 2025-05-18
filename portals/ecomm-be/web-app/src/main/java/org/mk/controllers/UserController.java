package org.mk.controllers;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.mk.graphql.GQLClientUsage;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/user")
@Tag(name = "User controller GQL")
public class UserController {

    @Inject
    GQLClientUsage graphqlClient;


    @GET
    @Path("/{id}")
    public Object getUserById(@PathParam("id") String id) {
        return graphqlClient.executeGetUser(id);
    }

}
