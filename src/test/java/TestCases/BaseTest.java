package TestCases;

import Helpers.CustomDriverManager;
import Helpers.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public WebDriver driver;
    public static String baseURI;
    @BeforeClass
    public void before() {
        try {
            //Configuraciones para UI testing
            CustomDriverManager.setDriver(Utils.getBrowser(), Utils.isHeadless());
            driver = CustomDriverManager.getDriver();
            String url_application = Utils.getProperty("environments", "siteURL-" + Utils.getEnvironment());
            driver.get(url_application);
            /*WebDriverManager.chromedriver().setup();
            ChromeDriver driver = new ChromeDriver();*/


            //configuraciones para API Testing
            String ambiente = Utils.getEnvironment();
            System.out.println(ambiente);
            baseURI = Utils.getProperty("environments", "apiURL-" + Utils.getEnvironment());
            System.out.println("la url base es " + baseURI);
        } catch (Exception ex) {
            System.out.println("ERROR BEFORE: " + ex);
        }

    }

    @AfterClass
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
