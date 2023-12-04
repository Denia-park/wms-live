package com.ejoongseok.wmslive.location.domain;

import org.springframework.util.Assert;

public class Location {
    private final String locationBarcode;
    private final StorageType storageType;
    private final UsagePurpose usagePurpose;
    private Long locationNo;

    public Location(final String locationBarcode,
                    final StorageType storageType,
                    final UsagePurpose usagePurpose) {
        validateConstructor(locationBarcode, storageType, usagePurpose);

        this.locationBarcode = locationBarcode;
        this.storageType = storageType;
        this.usagePurpose = usagePurpose;
    }

    private void validateConstructor(final String locationBarcode, final StorageType storageType, final UsagePurpose usagePurpose) {
        Assert.hasText(locationBarcode, "locationBarcode는 필수입니다.");
        Assert.notNull(storageType, "storageType는 필수입니다.");
        Assert.notNull(usagePurpose, "usagePurpose는 필수입니다.");
    }

    public void assignId(final Long locationNo) {
        this.locationNo = locationNo;
    }

    public Long getLocationNo() {
        return this.locationNo;
    }
}
