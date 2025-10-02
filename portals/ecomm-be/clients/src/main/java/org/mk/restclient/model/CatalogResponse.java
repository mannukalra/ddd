package org.mk.restclient.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mk.restclient.jsondser.CatalogRespJsonDeserializer;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = CatalogRespJsonDeserializer.class)
public class CatalogResponse {
    private String marketId;
    private String spec;
    private List<Offering> offerings;
}
