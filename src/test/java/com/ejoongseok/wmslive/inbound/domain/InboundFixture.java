package com.ejoongseok.wmslive.inbound.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class InboundFixture {
    private Long inboundNo = 1L;
    private String title = "title";
    private String description = "description";
    private LocalDateTime orderRequestedAt = LocalDateTime.now();
    private LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
    private List<InboundItemFixture> inboundItems = List.of(InboundItemFixture.aInboundItem());

    public InboundFixture() {
    }

    public static InboundFixture aInbound() {
        return new InboundFixture();
    }

    public InboundFixture inboundNo(final Long inboundNo) {
        this.inboundNo = inboundNo;
        return this;
    }

    public InboundFixture title(final String title) {
        this.title = title;
        return this;
    }

    public InboundFixture description(final String description) {
        this.description = description;
        return this;
    }

    public InboundFixture orderRequestedAt(final LocalDateTime orderRequestedAt) {
        this.orderRequestedAt = orderRequestedAt;
        return this;
    }

    public InboundFixture estimatedArrivalAt(final LocalDateTime estimatedArrivalAt) {
        this.estimatedArrivalAt = estimatedArrivalAt;
        return this;
    }

    public InboundFixture inboundItems(final InboundItemFixture... inboundItems) {
        this.inboundItems = Arrays.asList(inboundItems);
        return this;
    }

    public Inbound build() {
        return new Inbound(
                inboundNo,
                title,
                description,
                orderRequestedAt,
                estimatedArrivalAt,
                buildInboundItems()
        );
    }

    private List<InboundItem> buildInboundItems() {
        return inboundItems.stream()
                .map(InboundItemFixture::build)
                .toList();
    }
}
