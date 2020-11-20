package org.tutorials.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.tutorials.pojos.Student;
import org.tutorials.tests.TestBase;

public class RequestFactory extends TestBase {

    @Step("Getting all students' information from the DB")
    public Response getAllStudents() {

        return restClient.doGetRequest("/list");
    }

    @Step("Creating a new student with name: {student.firstName} {student.lastName} and email: {student.email}")
    public Response createNewStudent(String url, Student student) {

        return restClient.doPostRequest(url, student);
    }

    @Step("Updating a student with name: {student.firstName} {student.lastName} and email: {student.email}")
    public Response updateStudent(String url, Student student) {

        return restClient.doPutRequest("/" + student.getId(), student);
    }

    @Step("Deleting a student name: {student.firstName} {student.lastName} and email: {student.email}")
    public Response deleteStudent(int id) {

        return restClient.doDeleteRequest("/" + id);
    }

    @Step("Get student by ID")
    public Response getStudentById(int id) {

        return restClient.doGetRequest("/" + id);
    }
}
