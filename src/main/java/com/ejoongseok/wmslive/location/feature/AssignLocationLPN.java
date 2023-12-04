package com.ejoongseok.wmslive.location.feature;

import com.ejoongseok.wmslive.inbound.domain.LPN;
import com.ejoongseok.wmslive.inbound.domain.LPNRepository;
import com.ejoongseok.wmslive.location.domain.Location;
import com.ejoongseok.wmslive.location.domain.LocationRepository;
import org.springframework.util.Assert;

class AssignLocationLPN {
    private final LocationRepository locationRepository;
    private final LPNRepository lpnRepository;

    public AssignLocationLPN(final LocationRepository locationRepository, final LPNRepository lpnRepository) {
        this.locationRepository = locationRepository;
        this.lpnRepository = lpnRepository;
    }

    public void assignLocationLPN(final Request request) {
        final Location location = locationRepository.getByLocationBarcode(request.locationBarcode);
        final LPN lpn = lpnRepository.getByLpnBarcode(request.lpnBarcode);

        location.assignLPN(lpn);
    }

    public record Request(String locationBarcode, String lpnBarcode) {
        public Request {
            Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다.");
            Assert.hasText(lpnBarcode, "LPN 바코드는 필수입니다.");
        }
    }
}
