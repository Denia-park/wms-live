package com.ejoongseok.wmslive.inbound.feature;

import com.ejoongseok.wmslive.common.ApiTest;
import com.ejoongseok.wmslive.inbound.domain.InboundRepository;
import com.ejoongseok.wmslive.product.domain.ProductRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static com.ejoongseok.wmslive.product.fixture.ProductFixture.aProductFixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyLong;

class RegisterInboundTest extends ApiTest {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private InboundRepository inboundRepository;

    //setup을 2개 쓰고 싶으면 이름을 다르게 해야함 [동일하면 Override가 되기 때문에]

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        //given
        BDDMockito.given(productRepository.getBy(anyLong()))
                .willReturn(aProductFixture().build());
        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
        final Long productNo = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;
        final RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
                productNo,
                quantity,
                unitPrice,
                "description"
        );
        final List<RegisterInbound.Request.Item> inboundItems = List.of(inboundItem);
        final RegisterInbound.Request request = new RegisterInbound.Request(
                "title",
                "description",
                orderRequestedAt,
                estimatedArrivalAt,
                inboundItems
        );

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/inbounds")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        assertThat(inboundRepository.findAll()).hasSize(1);
    }
}
