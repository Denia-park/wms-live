package com.ejoongseok.wmslive.outbound.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterPackagingMaterialTest {

    private RegisterPackagingMaterial registerPackagingMaterial;

    @BeforeEach
    void setUp() {
        registerPackagingMaterial = new RegisterPackagingMaterial();
    }

    @Test
    @DisplayName("포장재를 등록한다.")
    void registerPackagingMaterial() {
        //given
        final Long innerWidthInMillimeters = 1000L;
        final Long innerHeightInMillimeters = 1000L;
        final Long innerLengthInMillimeters = 1000L;
        final Long outerWidthInMillimeters = 1000L;
        final Long outerHeightInMillimeters = 1000L;
        final Long outerLengthInMillimeters = 1000L;
        final Long weightInGrams = 100L;
        final Long maxWeightInGrams = 10000L;
        final RegisterPackagingMaterial.Request request = new RegisterPackagingMaterial.Request(
                "name",
                "code",
                innerWidthInMillimeters,
                innerHeightInMillimeters,
                innerLengthInMillimeters,
                outerWidthInMillimeters,
                outerHeightInMillimeters,
                outerLengthInMillimeters,
                weightInGrams,
                maxWeightInGrams,
                MaterialType.CORRUGATED_BOX
        );
        registerPackagingMaterial.request(request);


        //when


        //then
        assertThat(19).isEqualTo();
    }

    private enum MaterialType {
        CORRUGATED_BOX("골판지 상자");

        private final String description;

        MaterialType(final String description) {
            this.description = description;
        }
    }

    private class RegisterPackagingMaterial {
        public void request(final Request request) {

        }

        public record Request(String name, String code, long innerWidthInMillimeters, long innerHeightInMillimeters,
                              long innerLengthInMillimeters, long outerWidthInMillimeters,
                              long outerHeightInMillimeters, long outerLengthInMillimeters, Long weightInGrams,
                              Long maxWeightInGrams) {
        }
    }
}
