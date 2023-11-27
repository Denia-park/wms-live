package com.ejoongseok.wmslive.inbound.feature;

import com.ejoongseok.wmslive.inbound.domain.Inbound;
import com.ejoongseok.wmslive.inbound.domain.InboundFixture;
import com.ejoongseok.wmslive.inbound.domain.InboundRepository;
import com.ejoongseok.wmslive.inbound.domain.InboundStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class RejectInboundTest {

    private RejectInbound rejectInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        inboundRepository = Mockito.mock(InboundRepository.class);
        rejectInbound = new RejectInbound(inboundRepository);
    }

    @Test
    @DisplayName("입고를 반려/거부한다.")
    void rejectInbound() {
        //given
        final Inbound inbound = InboundFixture.anInbound().build();
        final Long inboundNo = 1L;
        final String rejectReason = "반려 사유";
        final RejectInbound.Request request = new RejectInbound.Request(rejectReason);
        Mockito.when(inboundRepository.getBy(inboundNo))
                .thenReturn(inbound);

        //when
        rejectInbound.request(inboundNo, request);

        //then
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.REJECTED);
    }
}
