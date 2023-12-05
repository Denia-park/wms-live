package com.ejoongseok.wmslive.outbound.feature;

import org.springframework.util.Assert;

class PackagingMaterialDimension {
    private final Long innerWidthInMillimeters;
    private final Long innerHeightInMillimeters;
    private final Long innerLengthInMillimeters;
    private final Long outerWidthInMillimeters;
    private final Long outerHeightInMillimeters;
    private final Long outerLengthInMillimeters;

    public PackagingMaterialDimension(final Long innerWidthInMillimeters,
                                      final Long innerHeightInMillimeters,
                                      final Long innerLengthInMillimeters,
                                      final Long outerWidthInMillimeters,
                                      final Long outerHeightInMillimeters,
                                      final Long outerLengthInMillimeters) {
        validateConstructor(innerWidthInMillimeters, innerHeightInMillimeters, innerLengthInMillimeters, outerWidthInMillimeters, outerHeightInMillimeters, outerLengthInMillimeters);

        this.innerWidthInMillimeters = innerWidthInMillimeters;
        this.innerHeightInMillimeters = innerHeightInMillimeters;
        this.innerLengthInMillimeters = innerLengthInMillimeters;
        this.outerWidthInMillimeters = outerWidthInMillimeters;
        this.outerHeightInMillimeters = outerHeightInMillimeters;
        this.outerLengthInMillimeters = outerLengthInMillimeters;
    }

    private void validateConstructor(final Long innerWidthInMillimeters,
                                     final Long innerHeightInMillimeters,
                                     final Long innerLengthInMillimeters,
                                     final Long outerWidthInMillimeters,
                                     final Long outerHeightInMillimeters,
                                     final Long outerLengthInMillimeters) {
        Assert.notNull(innerWidthInMillimeters, "내부 폭은 필수입니다.");
        if (innerWidthInMillimeters < 1L) {
            throw new IllegalArgumentException("내부 폭은 1mm 이상이어야 합니다.");
        }
        Assert.notNull(innerHeightInMillimeters, "내부 높이는 필수입니다");
        if (innerHeightInMillimeters < 1L) {
            throw new IllegalArgumentException("내부 높이는 1mm 이상이어야 합니다.");
        }
        Assert.notNull(innerLengthInMillimeters, "내부 길이는 필수입니다");
        if (innerLengthInMillimeters < 1L) {
            throw new IllegalArgumentException("내부 길이는 1mm 이상이어야 합니다.");
        }
        Assert.notNull(outerWidthInMillimeters, "외부 폭은 필수입니다.");
        if (outerWidthInMillimeters < 1L) {
            throw new IllegalArgumentException("외부 폭은 1mm 이상이어야 합니다.");
        }
        Assert.notNull(outerHeightInMillimeters, "외부 높이는 필수입니다.");
        if (outerHeightInMillimeters < 1L) {
            throw new IllegalArgumentException("외부 높이는 1mm 이상이어야 합니다.");
        }
        Assert.notNull(outerLengthInMillimeters, "외부 길이는 필수입니다.");
        if (outerLengthInMillimeters < 1L) {
            throw new IllegalArgumentException("외부 길이는 1mm 이상이어야 합니다.");
        }
    }
}