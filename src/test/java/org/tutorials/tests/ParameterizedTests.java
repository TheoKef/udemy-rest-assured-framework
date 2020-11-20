package org.tutorials.tests;

import com.github.javafaker.Faker;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.tutorials.pojos.Student;
import org.tutorials.requests.RequestFactory;
import org.tutorials.specs.SpecificationFactory;
import org.tutorials.tags.Regression;

import java.util.Map;

@RunWith(DataProviderRunner.class)
public class ParameterizedTests extends TestBase {

    @DataProvider
    public static Object[][] students() {
        return new Object[][]{
                { createStudentWithMissingInfo() },
                { createStudentWithMissingInfo() },
                { createStudentWithMissingInfo() }
        };
    }

    @Test
    @Category(Regression.class)
    @Tag("Regression")
    @DisplayName("Add student with missing information - Negative path")
    @UseDataProvider("students")
    public void addStudent(Student student){

        RequestFactory requestFactory = new RequestFactory();
        Object errors = requestFactory.createNewStudent("", student)
                .then()
                .spec(SpecificationFactory.getGenericResponseSpec())
                .log().body()
                .extract()
                .body()
                .jsonPath().getMap("$").get("fieldErrors");

        Map<String, String> result = (Map<String, String>)errors;
        for(String r : result.keySet()) {
            String value = result.get(r);

            assert value.equals("may not be empty");
        }
    }

    private static Student createStudentWithMissingInfo() {
        Faker fake = new Faker();

        return new Student(fake.name().firstName(), fake.name().lastName(), fake.internet().emailAddress() );
    }
}
