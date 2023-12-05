package com.ejoongseok.wmslive.outbound.feature;

import org.springframework.util.Assert;

class PackagingMaterial {
    private final String name;
    private final String code;
    private final PackagingMaterialDimension packagingMaterialDimension;
    private final Long weightInGrams;
    private final Long maxWeightInGrams;
    private final MaterialType corrugatedBox;
    private Long packagingMaterialNo;

    public PackagingMaterial(final String name,
                             final String code,
                             final PackagingMaterialDimension packagingMaterialDimension,
                             final Long weightInGrams,
                             final Long maxWeightInGrams,
                             final MaterialType corrugatedBox) {
        validateConstructor(name, code, weightInGrams, maxWeightInGrams, corrugatedBox);

        this.name = name;
        this.code = code;
        this.packagingMaterialDimension = packagingMaterialDimension;
        this.weightInGrams = weightInGrams;
        this.maxWeightInGrams = maxWeightInGrams;
        this.corrugatedBox = corrugatedBox;
    }

    private void validateConstructor(final String name, final String code, final Long weightInGrams, final Long maxWeightInGrams, final MaterialType corrugatedBox) {
        Assert.hasText(name, "포장재 이름은 필수입니다.");
        Assert.hasText(code, "포장재 코드는 필수입니다.");
        Assert.notNull(weightInGrams, "무게는 필수입니다.");
        if (weightInGrams < 1L) {
            throw new IllegalArgumentException("무게는 1g 이상이어야 합니다.");
        }
        Assert.notNull(maxWeightInGrams, "최대 무게는 필수입니다.");
        if (maxWeightInGrams < 1L) {
            throw new IllegalArgumentException("최대 무게는 1g 이상이어야 합니다.");
        }
        Assert.notNull(corrugatedBox, "포장재 종류는 필수입니다.");
    }

    public void assignNo(final Long packagingMaterialNo) {
        this.packagingMaterialNo = packagingMaterialNo;
    }

    public Long getPackagingMaterialNo() {
        return this.packagingMaterialNo;
    }
}
