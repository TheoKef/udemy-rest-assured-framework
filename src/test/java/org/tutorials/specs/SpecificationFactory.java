package org.tutorials.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.tutorials.tests.TestBase;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class SpecificationFactory extends TestBase {

    public static synchronized ResponseSpecification getGenericResponseSpec() {

        ResponseSpecBuilder responseSpec = new ResponseSpecBuilder();
        responseSpec.expectHeader("Content-Type", "application/json;charset=UTF-8");
        responseSpec.expectHeader("Transfer-Encoding", "chunked");
        responseSpec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);

        return responseSpec.build();
    }

    public static synchronized RequestSpecification logPayloadResponseInfo() {

        RequestSpecBuilder requestSpec = new RequestSpecBuilder();
        if (propertyReader.getProperty("allure.log").equals("enable")) {
            requestSpec.addFilter(new AllureRestAssured());
        }

        return requestSpec.build();
    }
}
