package TestCases;

import Helpers.DriverManager;
import Helpers.Utils;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    public WebDriver driver;
    public static String baseURI;
    @BeforeMethod
    public void before() {
        try {
            //Configuraciones para UI testing
            /*DriverManager.setDriver(Utils.getBrowser(), Utils.isHeadless());
            driver = DriverManager.getDriver();
            String url_application = Utils.getProperty("environments", "siteURL-" + Utils.getEnvironment());
            driver.get(url_application);*/

            //configuraciones para API Testing
            baseURI = Utils.getProperty("environments", "apiURL-" + Utils.getEnvironment());
            System.out.println("la url base es " + baseURI);
        } catch (Exception ex) {
            System.out.println("ERROR BEFORE: " + ex);
        }

    }

    @AfterMethod
    public void after() {
        try {
            if (driver != null) {
                driver.quit();
            }
            if(Utils.getBrowser().equals("firefox")) {
                Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe /T");
                Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe /T");
            }
        } catch(Exception ex){
            System.out.println("ERROR AFTER METHOD: " + ex);
        }
    }
}
