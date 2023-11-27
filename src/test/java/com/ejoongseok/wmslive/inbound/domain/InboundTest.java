package com.ejoongseok.wmslive.inbound.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InboundTest {

    @Test
    @DisplayName("입고를 승인한다.")
    void confirmed() {
        //given
        final Inbound inbound = InboundFixture.anInbound().build();
        final InboundStatus beforeStatus = inbound.getStatus();

        //when
        inbound.confirmed();

        //then
        assertEquals(beforeStatus, InboundStatus.REQUESTED);
        assertEquals(inbound.getStatus(), InboundStatus.CONFIRMED);
    }

    @Test
    @DisplayName("입고를 승인한다. - [실패] 입고의 상태가 요청이 아닌 경우 예외가 발생한다.")
    void fail_invalid_status_confirmed() {
        //given
        final Inbound confirmedInbound = InboundFixture.anInboundWithConfirmed().build();

        //when, then
        assertThatThrownBy(() -> {
            confirmedInbound.confirmed();
        })
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("입고 요청 상태가 아닙니다.");
    }

    @Test
    @DisplayName("입고를 반려/거부하면 입고의 상태가 REJECTED가 된다.")
    void reject() {
        //given
        final Inbound inbound = InboundFixture.anInbound().build();
        final InboundStatus beforeStatus = inbound.getStatus();

        //when
        final String rejectionReason = "반려 사유";
        inbound.reject(rejectionReason);

        //then
        assertEquals(beforeStatus, InboundStatus.REQUESTED);
        assertEquals(inbound.getStatus(), InboundStatus.REJECTED);
    }

    @Test
    @DisplayName("입고를 반려/거부한다. - [실패] 입고의 상태가 요청이 아닌 경우 예외가 발생한다.")
    void fail_invalid_status_reject() {
        //given
        final Inbound confirmedInbound = InboundFixture.anInboundWithConfirmed().build();

        //when, then
        assertThatThrownBy(() -> {
            confirmedInbound.reject("rejectionReason");
        })
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("입고 요청 상태가 아닙니다.");
    }
}
