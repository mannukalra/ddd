package org.mk.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.mk.restclient.operations.OfferingsOperation;

@Path("/api/tech")
@Tag(name = "Technical controller")
public class TechnicalController {

    @Inject
    OfferingsOperation offeringsOperation;


    @GET
    public Response resetCache() {
        offeringsOperation.resetCache();
        return Response.ok().build();
    }

}
