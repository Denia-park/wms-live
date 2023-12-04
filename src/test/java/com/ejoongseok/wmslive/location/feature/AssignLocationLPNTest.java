package com.ejoongseok.wmslive.location.feature;

import com.ejoongseok.wmslive.common.ApiTest;
import com.ejoongseok.wmslive.common.Scenario;
import com.ejoongseok.wmslive.location.domain.Location;
import com.ejoongseok.wmslive.location.domain.LocationLPN;
import com.ejoongseok.wmslive.location.domain.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AssignLocationLPNTest extends ApiTest {
    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setUpAssignLocationLPN() {
        Scenario
                .registerProduct().request()
                .registerInbound().request()
                .confirmInbound().request()
                .registerLPN().request()
                .registerLocation().request();
    }

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    @Transactional
    void assignLocationLPN() {
        Scenario.assignLocationLPN().request();

        //then
        assertAssignLocationLPN();
    }

    private void assertAssignLocationLPN() {
        final String locationBarcode = "A-1-1";
        final Location location = locationRepository.getByLocationBarcode(locationBarcode);
        final List<LocationLPN> locationLPNList = location.getLocationLPNList();
        final LocationLPN locationLPN = locationLPNList.get(0);

        assertThat(location.getLocationLPNList()).hasSize(1);
        assertThat(locationLPN.getInventoryQuantity()).isEqualTo(1L);
    }
}
