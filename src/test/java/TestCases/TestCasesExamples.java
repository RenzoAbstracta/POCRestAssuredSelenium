package TestCases;

import DataProviders.DataProviderClass;
import Endpoints.RequestExample;
import Entities.Pay;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;

import org.testng.annotations.Parameters;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;
import DataProviders.csv.DefaultCSVDataProvider;

import java.util.*;

import static io.restassured.RestAssured.given;

public class TestCasesExamples extends BaseTest {

    @Test
    @Description("Esto es una peticion GET basica")
    public void Test01() throws Exception {
        RequestExample.getSimpleTest(baseURI);
    }

    @Test
    @Description("Esto es una peticion POST basica")
    public void Test02() throws Exception {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "Alejandra");
        map.put("job", "Costumer Success");

        RequestExample.postSimpleTest(baseURI, map);
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
    @Description("Esto es un simple test de UI con selemium")
    public void Test03() throws InterruptedException {
        System.out.println(driver.getTitle());
        Thread.sleep(10000);
        Assert.assertEquals(driver.getTitle(), "Google");
    }

    @Test(dataProvider = "data-provider-default-csv", dataProviderClass = DataProviderClass.class)
    @Description("Ejemplo de lectura de un archivo csv")
    public void testOneLineCSV(String name, String rol) {
        System.out.println(name + '\t' + rol);
    }
    

}
