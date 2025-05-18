package org.mk.restclient.operations;

import io.quarkus.cache.CacheInvalidate;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.mk.restclient.OfferingsRestClient;
import org.mk.restclient.model.Offering;
import io.quarkus.cache.CacheResult;
import java.util.List;

@ApplicationScoped
public class OfferingsOperation {

    @RestClient
    OfferingsRestClient offeringsRestClient;

    @CacheResult(cacheName= "offerings-cache")
    public List<Offering> getOfferings() {
        return offeringsRestClient.getOfferings();
    }

    public void addOfferings(List<Offering> addedOfferings) {
        offeringsRestClient.addOfferings(addedOfferings);
    }

    public void removeOfferings(String offeringId) {
        offeringsRestClient.removeOfferings(offeringId);
    }

    @CacheInvalidate(cacheName = "offerings-cache")
    public void resetCache(){
        System.out.println("Cache reset for >>>> offerings-cache");
    }
}
