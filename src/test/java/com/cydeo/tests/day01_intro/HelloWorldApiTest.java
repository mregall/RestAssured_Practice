package com.cydeo.tests.day01_intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

public class HelloWorldApiTest {
    String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";

    @DisplayName("Hello World GET request")
    @Test
    public void helloWorldGetRequestTest(){
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        //print the body
        response.prettyPrint();

        //print status code
        response.statusCode();
        System.out.println("response.statusLine() = " + response.statusLine());

        //assert that the response is correct
        Assertions.assertEquals(200, response.statusCode());

        //Declare statusCode Variable and assign the response statusCode then assert
        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode);

        //make sure hello world is correct
        Assertions.assertTrue(response.asString().contains("Hello World!"));

    }
   /* When User Sends get request to API Endpoint:
            "https://reqres.in/api/users/5"
    Then Response status code should be 200
    And Response body should contain user info "Charles"*/




}