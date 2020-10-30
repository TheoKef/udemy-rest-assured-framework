package org.tutorials.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.tutorials.utils.PropertyReader;

public class TestBase {

    public static PropertyReader propertyReader;

    @BeforeClass
    public static void initializeURL() {
        propertyReader = PropertyReader.getInstance();

        RestAssured.baseURI = propertyReader.getProperty("sut.base.url");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("sut.port"));
    }
}
