package com.ejoongseok.wmslive.location.feature;

import com.ejoongseok.wmslive.common.Scenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class AssignLocationLPNApi {
    private String locationBarcode = "A-1-1";
    private String lpnBarcode = "LPN-0001";

    public AssignLocationLPNApi locationBarcode(String locationBarcode) {
        this.locationBarcode = locationBarcode;
        return this;
    }

    public AssignLocationLPNApi lpnBarcode(String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public Scenario request() {
        //given
        final AssignLocationLPN.Request request = new AssignLocationLPN.Request(
                locationBarcode,
                lpnBarcode
        );

        //when
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/locations/assign-lpn")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        return new Scenario();
    }
}
