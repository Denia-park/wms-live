package com.ejoongseok.wmslive.inbound.feature;

import com.ejoongseok.wmslive.common.ApiTest;
import com.ejoongseok.wmslive.common.Scenario;
import com.ejoongseok.wmslive.inbound.domain.InboundRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterInboundTest extends ApiTest {
    @Autowired
    private InboundRepository inboundRepository;

    //setup을 2개 쓰고 싶으면 이름을 다르게 해야함 [동일하면 Override가 되기 때문에]

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        //given
        Scenario.registerProduct().request()
                .registerInbound().request();

        assertThat(inboundRepository.findAll()).hasSize(1);
    }
}
