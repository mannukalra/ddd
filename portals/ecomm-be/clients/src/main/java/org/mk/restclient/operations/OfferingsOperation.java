package org.mk.restclient.operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.cache.CacheInvalidate;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.mk.restclient.OfferingsRestClient;
import org.mk.restclient.model.CatalogResponse;
import org.mk.restclient.model.Offering;
import io.quarkus.cache.CacheResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@ApplicationScoped
public class OfferingsOperation {

    @RestClient
    OfferingsRestClient offeringsRestClient;

    @CacheResult(cacheName= "offerings-cache")
    public List<Offering> getOfferings() {
        return offeringsRestClient.getOfferings();
    }

    public List<Offering> getJsonOfferings() {
        List<Offering> result = null;
        try {
            Path filePath = Paths.get("D:\\DND\\Idea\\ddd\\portals\\ecomm-be\\clients\\src\\main\\resources\\json\\catalog-resp.json");
            String content = Files.readString(filePath);
            ObjectMapper mapper = new ObjectMapper();
            var catalogResponse = mapper.readValue(content, CatalogResponse.class);
            result = catalogResponse.getOfferings();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
