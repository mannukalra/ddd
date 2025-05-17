package org.mk.web;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mk.restclient.OfferingsRestClient;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/plans")
@Tag(name = "Plans controller")
public class PlansController {

    @RestClient
    OfferingsRestClient offeringsRestClient;

    List<String> plans = new ArrayList<>(Arrays.asList("Fiber 100", "Antina 100", "Fiber 200"));

    @GET
    public List<String> getPlans() {
        return offeringsRestClient.getOfferings();
    }

    @POST
    public List<String> addPlans(List<String> addedPlans) {
        plans.addAll(addedPlans);
        return plans;
    }

    @DELETE
    public List<String> removePlans(List<String> removedPlans) {
        System.out.println(plans + "  Before");
        plans.removeAll(removedPlans);
        System.out.println(plans + "  after");
        return plans;
    }

}
