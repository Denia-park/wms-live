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
        final RegisterLocation.Request request = new RegisterLocation.Request();
        registerLocation.request(request);


        //when


        //then
        assertThat(19).isEqualTo();
    }

    private class RegisterLocation {
        public void request() {
            throw new UnsupportedOperationException("RegisterLocation::request not implemented yet");
        }

        public record Request() {
        }
    }
}
