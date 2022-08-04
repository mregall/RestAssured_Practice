package com.cydeo.tests.day03_parameters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

 /*   Given acceptance type is Json
          And ID parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload(body)
       */


public class SpartanApiWithParamsTest {
    String url = "http://3.93.242.50:8000/api/spartans";

    @DisplayName("Get Spartan API with Params")
    @Test
    public void getASingleSpartan(){
        given().accept(ContentType.JSON)
                .when().get(url+"5");

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get(url+"/{id}");

        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("Content type = " + response.getHeader("content-type"));

        assertEquals("application/json", response.contentType());
        assertTrue(response.asString().contains("Blythe"));
    }

    /**
     *  Given accept type is Json
     *         And Id parameter value is 500
     *         When user sends GET request to /api/spartans/{id}
     *         Then response status code should be 404
     *         And response content-type: application/json
     *         And "Not Found" message should be in response payload
     */

    @DisplayName("Get Api Spartans - Not Found")
    @Test
    public void getSingleSpartanNotFound(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(url + "/{id}");

        System.out.println("status code = " + response.statusCode());
        assertEquals(404, response.statusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

        response.prettyPrint();
        assertTrue(response.asString().contains("Not Found"));
    }



}














