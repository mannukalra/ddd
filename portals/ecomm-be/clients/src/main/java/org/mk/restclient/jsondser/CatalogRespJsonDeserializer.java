package org.mk.restclient.jsondser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.mk.restclient.model.CatalogResponse;
import org.mk.restclient.model.Offering;

import java.io.IOException;
import java.util.List;

public class CatalogRespJsonDeserializer extends JsonDeserializer<CatalogResponse> {

    public CatalogResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Root root = jp.readValueAs(Root.class);

        CatalogResponse resp = new CatalogResponse();
        if (root != null && root.offerings != null) {
            var offerings = root.offerings.stream().map(off ->
                Offering.builder().id(off.offering.id).name(off.offering.name).businessType(off.businessType).build()).toList();
            resp.setSpec(root.spec);
            resp.setMarketId(root.marketId);
            resp.setOfferings(offerings);
        }
        return resp;
    }

    private static class Root {
        public String marketId;
        public String spec;
        public List<Offrng> offerings;
    }

    private static class Offrng {
        public IdAndName offering;
        public String businessType;
    }

    private static class IdAndName {
        public String id;
        public String name;
    }

}
