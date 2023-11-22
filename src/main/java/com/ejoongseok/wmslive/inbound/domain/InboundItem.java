package com.ejoongseok.wmslive.inbound.domain;

import com.ejoongseok.wmslive.product.domain.Product;
import com.google.common.annotations.VisibleForTesting;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Table(name = "inbound_item")
@Comment("입고 상품")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InboundItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inbound_item_no")
    @Comment("입고 상품 번호")
    private Long inboundItemNo;
    @Comment("상품")
    @JoinColumn(name = "product_no", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Column(name = "quantity", nullable = false)
    @Comment("수량")
    private Long quantity;
    @Column(name = "unit_price", nullable = false)
    @Comment("단가")
    private Long unitPrice;
    @Column(name = "description", nullable = false)
    @Comment("품목 설명")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inbound_no", nullable = false)
    @Comment("입고 번호")
    private Inbound inbound;

    public InboundItem(final Product product,
                       final Long quantity,
                       final Long unitPrice,
                       final String description) {
        validateConstructor(product, quantity, unitPrice, description);
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    @VisibleForTesting
    InboundItem(final Long inboundItemNo,
                final Product product,
                final Long quantity,
                final Long unitPrice,
                final String description) {
        this(product, quantity, unitPrice, description);
        this.inboundItemNo = inboundItemNo;
    }

    private void validateConstructor(final Product product,
                                     final Long quantity,
                                     final Long unitPrice,
                                     final String description) {
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.notNull(quantity, "수량은 필수입니다.");
        if (1 > quantity) {
            throw new IllegalArgumentException("수량은 1보다 작을 수 없습니다.");
        }
        Assert.notNull(unitPrice, "단가는 필수입니다.");
        if (0 > unitPrice) {
            throw new IllegalArgumentException("단가는 0보다 작을 수 없습니다.");
        }
        Assert.hasText(description, "품목 설명은 필수입니다.");
    }

    public void assignInbound(final Inbound inbound) {
        Assert.notNull(inbound, "입고는 필수입니다.");
        this.inbound = inbound;
    }
}
