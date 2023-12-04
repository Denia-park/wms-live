package com.ejoongseok.wmslive.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LPNRepository extends JpaRepository<LPN, Long> {
    @Query("select lpn from LPN lpn where lpn.lpnBarcode = :lpnBarcode")
    Optional<LPN> findByLpnBarcode(String lpnBarcode);

    default LPN getByLpnBarcode(final String lpnBarcode) {
        return findByLpnBarcode(lpnBarcode)
                .orElseThrow(() -> new IllegalArgumentException("해당 LPN 바코드를 찾을 수 없습니다.%s".formatted(lpnBarcode)));
    }
}
