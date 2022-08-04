package com.cydeo.tests.day02_headers;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHeadersTest {
    /**
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I Should see all Spartans' data in JSON format
     */
    String url = "http://3.93.242.50:8000/api/spartans";

    @DisplayName("Get /api/spartans and expect json response")
    @Test
    public void getAllSpartansHeaderTest() {
        when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON);
    }

    /**
     * Given Acceptance type is XML
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I Should see all Spartans' data in JSON format
     */


    @DisplayName("Get Spartans Request with XML")
    @Test
    public void acceptTypeHeaderTest() {
        given().accept(ContentType.XML)
                .when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.XML);
    }

    /**
     * Given Acceptance type is application/json
     * • When I send a GET request to
     * -----------------------------
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And read headers
     */

    @DisplayName("Get Spartans Read Headers")
    @Test
    public void readHeadersTest() {
        //we need response object so that we can use the .getHeader method
        Response response = given().accept(ContentType.JSON)
                .when().get(url);

        System.out.println("Date header = " + response.getHeader("Date"));
        System.out.println("Content type = " + response.getHeader("Content-type"));
        System.out.println("Connection = " + response.getHeader("Connection"));
        //read the headers
        Headers headers = response.getHeaders();
        System.out.println("headers = " + headers);

        //ensure the header Keep-Alive is present
        //this is just a thing he picked because we were out of time.
        //not necessarily an important header
        assertTrue(response.getHeader("Keep-Alive") != null);

    }

}
