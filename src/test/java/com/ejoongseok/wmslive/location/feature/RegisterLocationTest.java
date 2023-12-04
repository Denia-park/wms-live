package com.ejoongseok.wmslive.location.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterLocationTest {

    private RegisterLocation registerLocation;

    @BeforeEach
    void setUp() {
        registerLocation = new RegisterLocation();
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
        registerLocation.request(request);


        //when


        //then
        assertThat(19).isEqualTo();
    }

    private enum StorageType {
        TOTE("토트 바구니");
        private final String description;

        StorageType(final String description) {
            this.description = description;
        }
    }

    private enum UsagePurpose {
        MOVE("이동");
        private final String description;

        UsagePurpose(final String description) {
            this.description = description;
        }
    }

    private class RegisterLocation {
        public void request() {
            throw new UnsupportedOperationException("RegisterLocation::request not implemented yet");
        }

        public record Request(String locationBarcode, StorageType storageType, UsagePurpose usagePurpose) {
        }
    }
}
