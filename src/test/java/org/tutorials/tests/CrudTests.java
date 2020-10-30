package org.tutorials.tests;

import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;

@Story("STORY: CRUD TESTING")
public class CrudTests extends TestBase {

    @DisplayName("Test to get all students from DB successfully")
    @Test
    public void getAllStudents() {

        RestAssured
                .given()
                .when()
                .get("/list")
                .then()
                .log().body()
                .statusCode(200);
    }
}
