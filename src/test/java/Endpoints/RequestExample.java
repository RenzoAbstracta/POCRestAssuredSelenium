package Endpoints;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestExample {

    public static String getSimpleTest(String baseurl) throws Exception {
        //Ejemplo de peticion basica con restassured
        Allure.step("Ejemplo paso 1 para el reporte");
        baseURI = baseurl;
        String response =  given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("data[1].first_name", equalTo("Janet"))
                .extract().body().asString();

        return response;

    }

    public static String postSimpleTest(String baseUrl, Map<String, Object> map) throws Exception {
         baseURI = baseUrl;
         String body = given()
                .log().all()
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201).extract().body().asString();

         return body;
    }
}
