package com.ejoongseok.wmslive.location.domain;

import com.ejoongseok.wmslive.inbound.domain.LPN;

public class LocationLPN {
    private final Location location;
    private final LPN lpn;
    private Long inventoryQuantity;

    public LocationLPN(final Location location, final LPN lpn) {
        this.location = location;
        this.lpn = lpn;
        this.inventoryQuantity = 1L;
    }

    public void increaseQuantity() {
        this.inventoryQuantity++;
    }

    public Long getInventoryQuantity() {
        return this.inventoryQuantity;
    }

    boolean matchLpnToLocation(final LPN lpn) {
        return this.lpn.equals(lpn);
    }
}
