package com.cydeo.tests.day02_headers;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiHelloTest {
    /*
    When I send GET request to http://3.93.242.50:8000/api/hello
    Then status code should be 200
    And response body should be equal to "Hello from Sparta"
    */

    String url = "http://3.93.242.50:8000/api/hello";

    @DisplayName("Hello Sparta")
    @Test
    public void helloApiTest(){
        Response response = when().get(url);

        System.out.println("Status Code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        response.prettyPrint();
        System.out.println("Response JSON body = " + response.asString());
        assertTrue(response.asString().contains("Hello from Sparta"));

        System.out.println("Content type = " + response.contentType());
        assertEquals("text/plain;charset=UTF-8", response.contentType());
    }

    @DisplayName("Get Hello BDD")
    @Test
    public void helloApiBBDTest(){
        //here we can send the request and verify the response in a single statement.
        //this is much easier than Apache Client, the old way
        when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=UTF-8");
    }


}
