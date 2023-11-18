package com.ejoongseok.wmslive.inbound.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterInboundTest {

    private RegisterInbound registerInbound;

    @BeforeEach
    void setUp() {
        registerInbound = new RegisterInbound();
    }

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        //given
        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
        final Long productNo = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;
        final RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
                productNo,
                quantity,
                unitPrice,
                "description"
        );
        final List<RegisterInbound.Request.Item> inboundItems = List.of(inboundItem);
        final RegisterInbound.Request request = new RegisterInbound.Request(
                "title",
                "description",
                orderRequestedAt,
                estimatedArrivalAt,
                inboundItems
        );
        registerInbound.request(request);

        //when


        //then
        assertThat(19).isEqualTo();
    }

    private class RegisterInbound {
        public void request(final Request request) {

        }

        public record Request(
                String title,
                String description,
                LocalDateTime orderRequestedAt,
                LocalDateTime estimatedArrivalAt,
                List<Item> inboundItems
        ) {
            public Request {
                Assert.hasText(title, "입고 제목은 필수입니다.");
                Assert.hasText(description, "입고 설명은 필수입니다.");
                Assert.notNull(orderRequestedAt, "입고 요청일은 필수입니다.");
                Assert.notNull(estimatedArrivalAt, "입고 예정일은 필수입니다.");
                Assert.notEmpty(inboundItems, "입고 품목은 필수입니다.");
            }

            public record Item(
                    Long productNo,
                    Long quantity,
                    Long unitPrice,
                    String description
            ) {
                public Item {
                    Assert.notNull(productNo, "상품 번호는 필수입니다.");
                    Assert.notNull(quantity, "수량은 필수입니다.");
                    if (1 > quantity) {
                        throw new IllegalArgumentException("수량은 0보다 작을 수 없습니다.");
                    }
                    Assert.notNull(unitPrice, "단가는 필수입니다.");
                    if (0 > unitPrice) {
                        throw new IllegalArgumentException("단가는 0보다 작을 수 없습니다.");
                    }
                    Assert.hasText(description, "품목 설명은 필수입니다.");
                }
            }
        }
    }
}
