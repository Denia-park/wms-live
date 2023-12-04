package com.ejoongseok.wmslive.location.domain;

import com.ejoongseok.wmslive.inbound.domain.LPN;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Entity
@Table(name = "location")
@Comment("로케이션")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_no")
    @Comment("로케이션 번호")
    private Long locationNo;
    @Column(name = "location_barcode")
    @Comment("로케이션 바코드")
    private String locationBarcode;
    @Enumerated(EnumType.STRING)
    @Column(name = "storage_type")
    @Comment("보관 타입")
    private StorageType storageType;
    @Enumerated(EnumType.STRING)
    @Column(name = "usage_purpose")
    @Comment("보관 목적")
    private UsagePurpose usagePurpose;

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

    public void assignLPN(final LPN lpn) {

    }
}
