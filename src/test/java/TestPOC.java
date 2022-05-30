import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPOC {

    @Test
    @Description("Esto es una prueba")
    public void testGetUsers(){
        baseURI = "https://reqres.in/api";

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("data[1].first_name", equalTo("Janet"));
    }

    @Test
    @Description("Esto es una prueba de coneccion a BD")
    public void test01() throws Exception {
        Assert.assertEquals(4,4);
    }

    @Test
    @Description("Esto es una prueba")
    public void test03(){
        Assert.assertTrue(true,"Este caso no falla");
    }
}
