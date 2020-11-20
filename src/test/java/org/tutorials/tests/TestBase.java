package org.tutorials.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.tutorials.requests.RestClient;
import org.tutorials.utils.PropertyReader;

public class TestBase {

    public static RestClient restClient;
    public static PropertyReader propertyReader;

    @Rule
    public TestRule listener = new TestWatcher() {

        @Override
        protected void succeeded(Description desc) {
            System.out.println("METHOD PASSED: " + desc.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description desc) {
            System.out.println("METHOD FAILED: " + desc.getMethodName() + "with exceptions: " + e.getMessage());
        }
    };

    @BeforeClass
    public static void initializeURL() {
        propertyReader = PropertyReader.getInstance();
        restClient = new RestClient();

        RestAssured.baseURI = propertyReader.getProperty("sut.base.url");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("sut.port"));
    }
}
