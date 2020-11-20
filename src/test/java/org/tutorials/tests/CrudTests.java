package org.tutorials.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.tutorials.pojos.Student;
import org.tutorials.requests.RequestFactory;
import org.tutorials.specs.SpecificationFactory;
import org.tutorials.tags.Regression;
import org.tutorials.tags.Smoke;

import java.util.ArrayList;
import java.util.List;

@Story("STORY: CRUD TESTING")
public class CrudTests extends TestBase {

    private final RequestFactory requestFactory;

    public CrudTests() {
        requestFactory = new RequestFactory();
    }

    @Category({Smoke.class, Regression.class})
    @DisplayName("Test to get all students from DB successfully")
    @Test
    public void getAllStudents() {

        requestFactory.getAllStudents()
                .then()
                .spec(SpecificationFactory.getGenericResponseSpec())
                .log().body()
                .statusCode(200);
    }

    @Category(Regression.class)
    @DisplayName("Test to create and verify a new student")
    @Test
    public void createNewStudent() {

        Faker fake = new Faker();

        List<String> courses = new ArrayList<>();
        courses.add(fake.educator().course());
        courses.add(fake.educator().course());
        courses.add(fake.educator().course());

        Student student = new Student();
        student.setFirstName(fake.name().firstName());
        student.setLastName(fake.name().lastName());
        student.setEmail(fake.internet().emailAddress());
        student.setProgramme(fake.educator().campus());
        student.setCourses(courses);

        // we used "" in the url because we will use the base url
        requestFactory.createNewStudent("", student)
                .then()
                .spec(SpecificationFactory.getGenericResponseSpec())
                .log().body()
                .statusCode(201);
    }
}
