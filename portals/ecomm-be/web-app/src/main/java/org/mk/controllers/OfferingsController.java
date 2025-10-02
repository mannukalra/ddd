package org.mk.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

import org.mk.restclient.model.Offering;
import org.mk.services.OfferingsService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/offerings")
@Tag(name = "Offerings controller")
public class OfferingsController {

    @Inject
    OfferingsService offeringsService;

    @GET
    public Response getAllOfferings() {
        List<Offering> offerings = offeringsService.getOfferings(null);
        return Response.ok().entity(offerings).build();
    }

    @GET
    @Path("/json-offerings")
    public Response getJsonOfferings() {
        List<Offering> offerings = offeringsService.getJsonOfferings();
        return Response.ok().entity(offerings).build();
    }

    @GET
    @Path("/{businessType}")
    public Response getOfferings(@PathParam("businessType") String businessType) {
        List<Offering> offerings = offeringsService.getOfferings(businessType);
        return Response.ok().entity(offerings).build();
    }

    @POST
    public Response addOfferings(List<Offering> addedOfferings) {
        offeringsService.addOfferings(addedOfferings);
        return Response.ok().build();
    }

    @DELETE
    public Response removeOfferings(String offeringId) {
        offeringsService.removeOfferings(offeringId);
        return Response.ok().build();
    }

}
