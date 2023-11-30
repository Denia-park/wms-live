package com.ejoongseok.wmslive.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LPNRepository extends JpaRepository<LPN, Long> {
    @Query("select lpn from LPN lpn where lpn.lpnBarcode = :lpnBarcode")
    Optional<LPN> findByLpnBarcode(String lpnBarcode);
}
