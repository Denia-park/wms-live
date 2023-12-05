package com.ejoongseok.wmslive.location.domain;

import com.ejoongseok.wmslive.inbound.domain.LPN;
import com.ejoongseok.wmslive.inbound.domain.LPNFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationTest {

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    void assignLPN() {
        final Location location = LocationFixture.aLocation().build();

        final LPN lpn = LPNFixture.anLPN().build();

        location.assignInventory(lpn);

        assertAssignLPN(location, 1L);
    }

    private void assertAssignLPN(final Location location, final Long expectedInventoryQuantity) {
        final List<Inventory> inventories = location.getInventories();
        final Inventory inventory = inventories.get(0);

        assertThat(inventories).hasSize(1);
        assertThat(inventory.getInventoryQuantity()).isEqualTo(expectedInventoryQuantity);
    }

    @Test
    @DisplayName("로케이션에 LPN을 할당한다. 이미 LPN이 존재하면, 생성하지 않고 재고만 증가시킨다.")
    void already_exists_assignLPN() {
        final Location location = LocationFixture.aLocation().build();

        final LPN lpn = LPNFixture.anLPN().build();
        final LPN lpn2 = LPNFixture.anLPN().build();

        location.assignInventory(lpn);
        location.assignInventory(lpn2);

        assertAssignLPN(location, 2L);
    }
}
