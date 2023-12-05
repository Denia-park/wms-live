package com.ejoongseok.wmslive.outbound.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Entity
@Table(name = "packaging_material")
@Comment("포장재")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PackagingMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "packaging_material_no")
    @Comment("포장재 번호")
    private Long packagingMaterialNo;
    @Column(name = "name", nullable = false)
    @Comment("포장재 이름")
    private String name;
    @Column(name = "code", nullable = false)
    @Comment("포장재 코드")
    private String code;
    @Embedded
    private PackagingMaterialDimension packagingMaterialDimension;
    @Column(name = "weight_in_grams", nullable = false)
    @Comment("무게(g)")
    private Long weightInGrams;
    @Column(name = "max_weight_in_grams", nullable = false)
    @Comment("최대 무게(g)")
    private Long maxWeightInGrams;
    @Enumerated(EnumType.STRING)
    @Column(name = "material_type", nullable = false)
    @Comment("포장재 종류")
    private MaterialType materialType;

    public PackagingMaterial(final String name,
                             final String code,
                             final PackagingMaterialDimension packagingMaterialDimension,
                             final Long weightInGrams,
                             final Long maxWeightInGrams,
                             final MaterialType materialType) {
        validateConstructor(name, code, weightInGrams, maxWeightInGrams, materialType);

        this.name = name;
        this.code = code;
        this.packagingMaterialDimension = packagingMaterialDimension;
        this.weightInGrams = weightInGrams;
        this.maxWeightInGrams = maxWeightInGrams;
        this.materialType = materialType;
    }

    private void validateConstructor(final String name, final String code, final Long weightInGrams, final Long maxWeightInGrams, final MaterialType materialType) {
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
        Assert.notNull(materialType, "포장재 종류는 필수입니다.");
    }

    public void assignNo(final Long packagingMaterialNo) {
        this.packagingMaterialNo = packagingMaterialNo;
    }

    public Long getPackagingMaterialNo() {
        return this.packagingMaterialNo;
    }
}
