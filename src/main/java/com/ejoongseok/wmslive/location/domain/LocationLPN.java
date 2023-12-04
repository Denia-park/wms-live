package com.ejoongseok.wmslive.location.domain;

import com.ejoongseok.wmslive.inbound.domain.LPN;

public class LocationLPN {
    private final Location location;
    private final LPN lpn;
    private Long inventoryQuantity;

    public LocationLPN(final Location location, final LPN lpn) {
        this.location = location;
        this.lpn = lpn;
        inventoryQuantity = 1L;
    }

    public void increaseQuantity() {
        inventoryQuantity++;
    }

    public LPN getLpn() {
        return this.lpn;
    }

    public Long getInventoryQuantity() {
        return inventoryQuantity;
    }
}
