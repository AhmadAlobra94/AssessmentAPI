package com.tejwal;

import Common.DateTimeHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Ahmad AlObra
 */
public class NewsletterSubscribePostTest {
    String endpoint = "https://www.tajawal.com";

    @Test
    public void testCreateNewSubscriber() throws JSONException {
        baseURI = endpoint;
        String url = endpoint+"/api/account/newsletter/subscribe";
        RequestSpecification requestSpecification = given();
        String name = "Test Name" ,
                email = "automation" + DateTimeHelper.getDateTime("yyMMddHHmmss", 0) + "@example.com";

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("email", email);

        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.header("cookie", "language=en; currency=AED; visid_incap_1982617=ojp4CKAOTgyJ8C7wsOoaOlEBC2EAAAAAQUIPAAAAAAChY1Wt0WofHICQJxJL0UL+; nlbi_1982617=GOPwDszy9Cu+NH5jBDQaAQAAAAD1MFb9QO+ICEBhy8icqDqj; WZRK_G=024b041a5d7c464f98bc694e872760d3; __ssid=ffed2215405b33d2b562a49ae3a43de; _gcl_au=1.1.659818292.1628111195; _gid=GA1.2.8987540.1628111196; _scid=dd4c09e9-a86b-4e7b-b9aa-e6d5b789af1c; _fbp=fb.1.1628111196665.2045043305; QuantumMetricUserID=0ae9f0732c2d48395bbbdc76c93f6b27; _sctr=1|1628110800000; QuantumMetricSessionID=e572178b55d85f9bbc092858dc91b8cb; incap_ses_578_1982617=WVSiBwJtQmeY8smCT3gFCPjPC2EAAAAA87rxI1bILP6UFawQ9dlxjw==; TEAL=v:817b12fd4e06600456536521248602279681a714924$t:1628166071549$sn:2$en:8$s:1628164092753%3Bexp-sess; _ga=GA1.2.1417327979.1628111195; WZRK_S_W65-668-WW6Z=%7B%22p%22%3A8%2C%22s%22%3A1628164094%2C%22t%22%3A1628164271%7D; _ga_L1FYQG124D=GS1.1.1628164091.2.1.1628164279.41");
        requestSpecification.body(requestParams.toString());

        Response response = requestSpecification.post(url);
        response.getBody().prettyPrint();
        assertEquals(response.getStatusCode(), 200);

        response.then().assertThat()
                .body("name", Matchers.equalTo(name))
                .body("email", Matchers.equalTo(email))
                .body("updated_at", Matchers.containsString(DateTimeHelper.getDateTime("yyyy-MM-dd", 0)))
                .body("created_at", Matchers.containsString(DateTimeHelper.getDateTime("yyyy-MM-dd", 0)));
    }

    @Test
    public void testIncorrectEmailFormat() throws JSONException {
        baseURI = endpoint;
        String url = endpoint+"/api/account/newsletter/subscribe";
        RequestSpecification requestSpecification = given();
        String name = "Test Name" ,
                email = "automation" + DateTimeHelper.getDateTime("yyMMddHHmmss", 0) + "example.com";

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("email", email);

        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.header("cookie", "language=en; currency=AED; visid_incap_1982617=ojp4CKAOTgyJ8C7wsOoaOlEBC2EAAAAAQUIPAAAAAAChY1Wt0WofHICQJxJL0UL+; nlbi_1982617=GOPwDszy9Cu+NH5jBDQaAQAAAAD1MFb9QO+ICEBhy8icqDqj; WZRK_G=024b041a5d7c464f98bc694e872760d3; __ssid=ffed2215405b33d2b562a49ae3a43de; _gcl_au=1.1.659818292.1628111195; _gid=GA1.2.8987540.1628111196; _scid=dd4c09e9-a86b-4e7b-b9aa-e6d5b789af1c; _fbp=fb.1.1628111196665.2045043305; QuantumMetricUserID=0ae9f0732c2d48395bbbdc76c93f6b27; _sctr=1|1628110800000; QuantumMetricSessionID=e572178b55d85f9bbc092858dc91b8cb; incap_ses_578_1982617=WVSiBwJtQmeY8smCT3gFCPjPC2EAAAAA87rxI1bILP6UFawQ9dlxjw==; TEAL=v:817b12fd4e06600456536521248602279681a714924$t:1628166071549$sn:2$en:8$s:1628164092753%3Bexp-sess; _ga=GA1.2.1417327979.1628111195; WZRK_S_W65-668-WW6Z=%7B%22p%22%3A8%2C%22s%22%3A1628164094%2C%22t%22%3A1628164271%7D; _ga_L1FYQG124D=GS1.1.1628164091.2.1.1628164279.41");
        requestSpecification.body(requestParams.toString());

        Response response = requestSpecification.post(url);
        response.getBody().prettyPrint();

        response.then().assertThat()
                .statusCode(400)
                .body("detail.email[0]", Matchers.equalTo("The email must be a valid email address."));
    }
    @Test
    public void testIncorrectEmailFormat_ThisScenarioShouldFailed() throws JSONException {
        baseURI = endpoint;
        String url = endpoint+"/api/account/newsletter/subscribe";
        RequestSpecification requestSpecification = given();
        String name = "Test Name" ,
                email = "automation" + DateTimeHelper.getDateTime("yyMMddHHmmss", 0) + "example.com";

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("email", email);

        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.header("cookie", "language=en; currency=AED; visid_incap_1982617=ojp4CKAOTgyJ8C7wsOoaOlEBC2EAAAAAQUIPAAAAAAChY1Wt0WofHICQJxJL0UL+; nlbi_1982617=GOPwDszy9Cu+NH5jBDQaAQAAAAD1MFb9QO+ICEBhy8icqDqj; WZRK_G=024b041a5d7c464f98bc694e872760d3; __ssid=ffed2215405b33d2b562a49ae3a43de; _gcl_au=1.1.659818292.1628111195; _gid=GA1.2.8987540.1628111196; _scid=dd4c09e9-a86b-4e7b-b9aa-e6d5b789af1c; _fbp=fb.1.1628111196665.2045043305; QuantumMetricUserID=0ae9f0732c2d48395bbbdc76c93f6b27; _sctr=1|1628110800000; QuantumMetricSessionID=e572178b55d85f9bbc092858dc91b8cb; incap_ses_578_1982617=WVSiBwJtQmeY8smCT3gFCPjPC2EAAAAA87rxI1bILP6UFawQ9dlxjw==; TEAL=v:817b12fd4e06600456536521248602279681a714924$t:1628166071549$sn:2$en:8$s:1628164092753%3Bexp-sess; _ga=GA1.2.1417327979.1628111195; WZRK_S_W65-668-WW6Z=%7B%22p%22%3A8%2C%22s%22%3A1628164094%2C%22t%22%3A1628164271%7D; _ga_L1FYQG124D=GS1.1.1628164091.2.1.1628164279.41");
        requestSpecification.body(requestParams.toString());

        Response response = requestSpecification.post(url);
        response.getBody().prettyPrint();

        response.then().assertThat()
                .statusCode(400)
                .body("detail.email[0]", Matchers.equalTo("test The email must be a valid email address. test"));
    }


}
