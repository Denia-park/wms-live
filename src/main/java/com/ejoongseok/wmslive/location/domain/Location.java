package com.ejoongseok.wmslive.location.domain;

import com.ejoongseok.wmslive.inbound.domain.LPN;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "location")
@Comment("로케이션")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Inventory> inventories = new ArrayList<>();
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

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void assignInventory(final LPN lpn) {
        Assert.notNull(lpn, "lpn는 필수입니다.");

        findInventoryBy(lpn)
                .ifPresentOrElse(
                        Inventory::increaseQuantity,
                        () -> assignNewLPN(lpn)
                );

    }

    private Optional<Inventory> findInventoryBy(final LPN lpn) {
        return inventories.stream()
                .filter(inventory -> inventory.matchLpnToLocation(lpn))
                .findFirst();
    }

    private boolean assignNewLPN(final LPN lpn) {
        return inventories.add(new Inventory(this, lpn));
    }
}
