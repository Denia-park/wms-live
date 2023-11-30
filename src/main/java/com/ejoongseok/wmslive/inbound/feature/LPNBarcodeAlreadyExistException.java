package com.ejoongseok.wmslive.inbound.feature;

public class LPNBarcodeAlreadyExistException extends RuntimeException {
    private static final String MESSAGE = "LPN 바코드 %s는 이미 존재합니다.";

    public LPNBarcodeAlreadyExistException(final String lpnBarcode) {
        super(MESSAGE.formatted(lpnBarcode));
    }
}
