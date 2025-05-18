package org.mk.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "portal")
public interface PortalProps {

    BusinessType businessType();

    interface BusinessType {
        String internet();
        String coax();
        String mobile();
    }
}