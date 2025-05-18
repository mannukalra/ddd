package org.mk.services;

import org.mk.restclient.model.Offering;

import java.util.List;

public interface OfferingsService {

    List<Offering> getOfferings(String businessTypeFilter);
    void addOfferings(List<Offering> addedOfferings);
    void removeOfferings(String offeringId);
}
