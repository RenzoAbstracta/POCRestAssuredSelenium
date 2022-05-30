package Integrations;

import Entities.Pay;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BrasilPOCTest {

    @Test
    @Description("Esto es una peticion GET basica")
    public void getSimpleTest() throws Exception {
        //Ejemplo de peticion basica con restassured
        baseURI = "https://reqres.in/api";
        Allure.step("Crear pago");
        given()
        .when()
                .get("/users")
        .then()
                .statusCode(200)
                .body("data[1].first_name", equalTo("Janet"));


    }

    @Test
    @Description("Esto es una peticion a la Base de datos que devuelve un objeto")
    public void obtencionDeDatosDesdeLaDB() throws Exception {
        //ejemplo de conexion a BD devolviendo una entidad u objeto para luego validar los datos.
        Allure.step("Validar DB");
        Pay unP = DB.UtilsDataBase.getInstancia().getPayingFromDB(1);
        System.out.println("el monto del pago es: "  + unP.getAmount());
    }

    @Test
    @Description("Esto es una peticion POST basica")
    public void postSimpleTest() throws Exception {
        baseURI = "https://reqres.in/api";

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "Alejandra");
        map.put("job", "Costumer Success");

        given()
                .log().all()
                .body(map.toString())
        .when()
                .post("/users")
        .then()
                .log().all()
                .statusCode(201);
    }
}
