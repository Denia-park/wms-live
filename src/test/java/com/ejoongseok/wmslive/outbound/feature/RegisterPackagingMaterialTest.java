package com.ejoongseok.wmslive.outbound.feature;

import com.ejoongseok.wmslive.outbound.domain.MaterialType;
import com.ejoongseok.wmslive.outbound.domain.PackagingMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterPackagingMaterialTest {

    private RegisterPackagingMaterial registerPackagingMaterial;
    private PackagingMaterialRepository pacakageMaterialRepository;

    @BeforeEach
    void setUp() {
        pacakageMaterialRepository = new PackagingMaterialRepository();
        registerPackagingMaterial = new RegisterPackagingMaterial(pacakageMaterialRepository);
    }

    @Test
    @DisplayName("포장재를 등록한다.")
    void registerPackagingMaterial() {
        //given
        final String name = "name";
        final String code = "code";
        final Long innerWidthInMillimeters = 1000L;
        final Long innerHeightInMillimeters = 1000L;
        final Long innerLengthInMillimeters = 1000L;
        final Long outerWidthInMillimeters = 1000L;
        final Long outerHeightInMillimeters = 1000L;
        final Long outerLengthInMillimeters = 1000L;
        final Long weightInGrams = 100L;
        final Long maxWeightInGrams = 10000L;
        final RegisterPackagingMaterial.Request request = new RegisterPackagingMaterial.Request(
                name,
                code,
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

        assertThat(pacakageMaterialRepository.findAll()).hasSize(1);
    }
}
