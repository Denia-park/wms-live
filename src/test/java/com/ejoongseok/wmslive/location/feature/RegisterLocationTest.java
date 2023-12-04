package com.ejoongseok.wmslive.location.feature;

import com.ejoongseok.wmslive.location.domain.LocationRepository;
import com.ejoongseok.wmslive.location.domain.StorageType;
import com.ejoongseok.wmslive.location.domain.UsagePurpose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterLocationTest {

    private RegisterLocation registerLocation;
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        locationRepository = new LocationRepository();
        registerLocation = new RegisterLocation(locationRepository);
    }

    @Test
    @DisplayName("로케이션을 등록한다")
    void registerLocation() {
        //given
        final String locationBarcode = "A-1-1";
        final StorageType storageType = StorageType.TOTE;
        final UsagePurpose usagePurpose = UsagePurpose.MOVE;

        final RegisterLocation.Request request = new RegisterLocation.Request(
                locationBarcode,
                storageType,
                usagePurpose
        );

        //when
        registerLocation.request(request);

        //then
        assertThat(locationRepository.findAll()).hasSize(1);
    }
}

