package com.tejwal;

import Common.DateTimeHelper;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.get;

/**
 * @author Ahmad AlObra
 */
public class FlightsSearchGetTest {

    String endpoint = "https://www.tajawal.com/api/v3/flights/flight/search";

    @Test
    public void testValidData() {
        String originId = "DXB",
                destinationId = "MNL",
                departure = DateTimeHelper.getDateTime("yyyy-MM-dd", 1),
                departureEnd = DateTimeHelper.getDateTime("yyyy-MM-dd", 2),
                preferredCabin = "Economy";

        String url = endpoint + "?query="+originId+"-"+destinationId+"/"+departure+"/"+departureEnd+"/"+preferredCabin+"/1Adult";
        System.out.println("URL: " + url);

        get(url).then().assertThat()
                .statusCode(200)
                .body("request.leg[0].originId", Matchers.equalTo(originId))
                .body("request.leg[0].destinationId", Matchers.equalTo(destinationId))
                .body("request.leg[0].departure", Matchers.equalTo(departure))
                .body("request.leg[0].preferredCabin", Matchers.equalTo(preferredCabin))
                .body("request.leg[1].departure", Matchers.equalTo(departureEnd));
    }
    @Test
    public void testInvalidOriginIdValue() {
        String originId = "DXB1",
                destinationId = "MNL",
                departure = DateTimeHelper.getDateTime("yyyy-MM-dd", 1),
                departureEnd = DateTimeHelper.getDateTime("yyyy-MM-dd", 2),
                preferredCabin = "Economy";

        String url = endpoint + "?query="+originId+"-"+destinationId+"/"+departure+"/"+departureEnd+"/"+preferredCabin+"/1Adult";
        System.out.println("URL: " + url);

        Response response = get(url);
        response.getBody().prettyPrint();
        response.then().assertThat()
                .statusCode(400);

    }

}
