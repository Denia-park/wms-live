package com.ejoongseok.wmslive.inbound.domain;

import java.time.LocalDateTime;

public class LPNFixture {

    private LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);
    private String lpnBarcode = "LPN-1";
    private InboundItemFixture inboundItemFixture = InboundItemFixture.aInboundItem();

    public static LPNFixture anLPN() {
        return new LPNFixture();
    }

    public LPNFixture expirationAt(final LocalDateTime expirationAt) {
        this.expirationAt = expirationAt;
        return this;
    }

    public LPNFixture lpnBarcode(final String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public LPNFixture inboundItemFixture(final InboundItemFixture inboundItemFixture) {
        this.inboundItemFixture = inboundItemFixture;
        return this;
    }

    public LPN build() {
        return new LPN(lpnBarcode, expirationAt, inboundItemFixture.build());
    }
}
