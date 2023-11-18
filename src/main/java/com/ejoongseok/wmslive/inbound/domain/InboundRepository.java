package com.ejoongseok.wmslive.inbound.domain;

import java.util.HashMap;
import java.util.Map;

public class InboundRepository {
    private final Map<Long, Inbound> inboundMap = new HashMap<>();
    private Long sequence = 1L;

    public void save(final Inbound inbound) {
        inbound.assignId(sequence++);
        inboundMap.put(inbound.getId(), inbound);
    }
}
