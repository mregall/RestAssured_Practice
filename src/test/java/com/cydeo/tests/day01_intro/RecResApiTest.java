package com.cydeo.tests.day01_intro;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecResApiTest {

    /**
     * When User sends GET Request to
     * https://reqres.in/api/users
     * <p>
     * Then Response status code should be 200
     * And Response body should contain "George"
     * And Header Content type should be json
     */

    String url = "https://reqres.in/api/users";

    @DisplayName("GET all users")
    @Test
    public void usersGetTest() {
        //by statically importing RestAssured we can start with when()
        //where before we had to write RestAssured.get(url)
        Response response = when().get(url);

        //Then response status code should be 200
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200, response.statusCode());

        //BDD syntax
        response.then().statusCode(200);
        response.then().assertThat().statusCode(200);

        //and response body should contain "George"
        System.out.println("Response JSON body = " + response.asString());
        assertTrue(response.asString().contains("George"));

        //and header content type should be json
        System.out.println("Content type header value = " + response.contentType());
        assertTrue(response.contentType().contains("application/json"));
    }

    @DisplayName("GET single user") //display is a smoke feature to make it easier to read.
    @Test
    public void getSingleUserApiTest() {

        Response response = when().get(url + "/5");
        System.out.println("Status Code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        //response body contain "Charles"
        response.prettyPrint();
        assertTrue(response.asString().contains("Charles"));
    }

        /*When Send get request to API Endpoint:
        "https://reqres.in/api/users/50"
        Then Response status code should be 404
        And Response body should contain "{}"*/

    @Test
    public void getSingleUserNegativeApiTest() {
        Response response = when().get(url + "/50");

        System.out.println("Status Code = " + response.statusCode());
        assertEquals(404, response.statusCode());

        System.out.println("JSON body = " + response.asString());
        assertEquals("{}", response.asString());


    }
}
