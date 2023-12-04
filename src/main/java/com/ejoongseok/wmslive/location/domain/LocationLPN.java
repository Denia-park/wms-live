package com.ejoongseok.wmslive.location.domain;

import com.ejoongseok.wmslive.inbound.domain.LPN;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;


@Entity
@Table(name = "location_lpn")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("로케이션 LPN")
public class LocationLPN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_lpn_no")
    @Comment("로케이션 LPN 번호")
    private Long locationLPNNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_no", nullable = false)
    @Comment("로케이션 번호")
    private Location location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lpn_no", nullable = false)
    @Comment("LPN 번호")
    private LPN lpn;
    @Column(name = "inventory_quantity", nullable = false)
    @Comment("재고 수량")
    private Long inventoryQuantity;

    LocationLPN(final Location location, final LPN lpn) {
        this.location = location;
        this.lpn = lpn;
        this.inventoryQuantity = 1L;
    }

    void increaseQuantity() {
        this.inventoryQuantity++;
    }

    public Long getInventoryQuantity() {
        return this.inventoryQuantity;
    }

    boolean matchLpnToLocation(final LPN lpn) {
        return this.lpn.equals(lpn);
    }
}
