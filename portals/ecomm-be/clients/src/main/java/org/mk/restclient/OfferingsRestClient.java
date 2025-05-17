package org.mk.restclient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/integ")
@RegisterRestClient(baseUri = "http://localhost:5050")
public interface OfferingsRestClient {

    @GET
    @Path("/offerings")
    List<String> getOfferings();
}