package com.ejoongseok.wmslive.location.feature;

import com.ejoongseok.wmslive.location.domain.Location;
import com.ejoongseok.wmslive.location.domain.LocationRepository;
import com.ejoongseok.wmslive.location.domain.StorageType;
import com.ejoongseok.wmslive.location.domain.UsagePurpose;
import org.springframework.util.Assert;

public class RegisterLocation {
    private final LocationRepository locationRepository;

    public RegisterLocation(final LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void request(final Request request) {
        final Location location = request.toDomain();

        locationRepository.save(location);
    }

    public record Request(String locationBarcode, StorageType storageType, UsagePurpose usagePurpose) {
        public Request {
            Assert.hasText(locationBarcode, "locationBarcode는 필수입니다.");
            Assert.notNull(storageType, "storageType는 필수입니다.");
            Assert.notNull(usagePurpose, "usagePurpose는 필수입니다.");
        }

        public Location toDomain() {
            return new Location(
                    locationBarcode,
                    storageType,
                    usagePurpose
            );
        }
    }
}
