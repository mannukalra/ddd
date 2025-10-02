package org.mk.services;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mk.config.PortalProps;
import org.mk.restclient.model.Offering;
import org.mk.restclient.operations.OfferingsOperation;

import java.util.List;

@ApplicationScoped
public class OfferingsServiceImpl implements OfferingsService{

    @Inject
    PortalProps portalProps;

    @Inject
    OfferingsOperation offeringsOperation;



    @Override
    public List<Offering> getOfferings(String businessTypeFilter) {
        if (StringUtil.isNullOrEmpty(businessTypeFilter)) {
            return offeringsOperation.getOfferings();
        }

        return offeringsOperation.getOfferings().stream()
                .filter(offering -> businessTypeFilter.equals(offering.getBusinessType()))
                .toList();
    }

    @Override
    public List<Offering> getJsonOfferings() {
        return offeringsOperation.getJsonOfferings();
    }

    @Override
    public void addOfferings(List<Offering> addedOfferings) {
        // default businessType to internet
        addedOfferings.forEach(offering -> {
                    if (offering.getBusinessType() == null) {
                        offering.setBusinessType(portalProps.businessType().internet());
                    }
                });
        offeringsOperation.addOfferings(addedOfferings);
    }

    @Override
    public void removeOfferings(String offeringId) {
        offeringsOperation.removeOfferings(offeringId);
    }
}
