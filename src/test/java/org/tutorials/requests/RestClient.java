package org.tutorials.requests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.tutorials.specs.SpecificationFactory;
import org.tutorials.tests.TestBase;

public class RestClient extends TestBase {

    public Response doGetRequest(String requestPath) {

        return given()
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .get(requestPath);
    }

    public Response doPostRequest(String uri, Object body) {

        return given()
                .contentType(ContentType.JSON)
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .body(body)
                .post(uri);
    }

    public Response doPutRequest(String res, Object body) {
        return given()
                .when()
                .body(body)
                .put(res);
    }

    public Response doPatchRequest(String res, Object body) {
        return given()
                .when()
                .body(body)
                .patch(res);
    }

    public Response doDeleteRequest(String res) {
        return given()
                .when()
                .delete(res);
    }
}
