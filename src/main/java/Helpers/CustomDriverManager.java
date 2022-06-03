package Helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.print.DocFlavor;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomDriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(String browserName, Boolean headless) throws Exception {
        driver.set(getNewDriverInstance(browserName, headless));
    }

    private static WebDriver getNewDriverInstance(String browserName, Boolean headless) throws Exception {
        WebDriver driver;
        try {
            switch (browserName.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions optionsFirefox = new FirefoxOptions();
                    if(headless)
                        optionsFirefox.setHeadless(true);
                    optionsFirefox.addArguments("start-maximized");
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), optionsFirefox);
                    break;
                case "edge":
                /*System.setProperty("webdriver.edge.driver","drivers\\msedgedriver2.exe");
                EdgeOptions optionsEdge = new EdgeOptions();
                if(headless)
                    optionsEdge.addArguments("headless");
                optionsEdge.addArguments("start-maximized");
                driver = new EdgeDriver(optionsEdge);*/
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    if (headless)
                        options.addArguments("--headless");
                    options.addArguments("start-maximized");
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
            }

            return driver;
        }  catch (Exception ex) {
            throw  new Exception("No se pudo levantar el driver "  + ex);
        }

    }
}

