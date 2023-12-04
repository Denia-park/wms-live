package com.ejoongseok.wmslive.location.domain;

import com.ejoongseok.wmslive.inbound.domain.LPN;
import com.ejoongseok.wmslive.inbound.domain.LPNFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationTest {

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    void assignLPN() {
        final Location location = LocationFixture.aLocation().build();

        final LPN lpn = LPNFixture.anLPN().build();

        location.assignLPN(lpn);

        assertThat(location.getLocationLPNList()).hasSize(1);
    }
}