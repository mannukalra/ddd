package org.mk.restclient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.mk.restclient.model.Offering;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/integ")
@RegisterRestClient(baseUri = "http://localhost:5050")
public interface OfferingsRestClient {

    @GET
    @Path("/offerings")
    List<Offering> getOfferings();

    @POST
    @Path("/offerings")
    List<Offering> addOfferings(List<Offering> addedOfferings);

    @DELETE
    @Path("/offerings")
    List<Offering> removeOfferings(String offeringId);

}