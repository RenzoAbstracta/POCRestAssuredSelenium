package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(String browserName, Boolean headless) {
        driver.set(getNewDriverInstance(browserName, headless));
    }

    private static WebDriver getNewDriverInstance(String browserName, Boolean headless) {
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver1.exe");
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                if(headless)
                    optionsFirefox.setHeadless(true);
                optionsFirefox.addArguments("start-maximized");
                driver =  new FirefoxDriver(optionsFirefox);
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver","drivers\\msedgedriver2.exe");
                EdgeOptions optionsEdge = new EdgeOptions();
                if(headless)
                    optionsEdge.addArguments("headless");
                optionsEdge.addArguments("start-maximized");
                driver = new EdgeDriver(optionsEdge);
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                if (headless)
                    options.addArguments("--headless");
                options.addArguments("start-maximized");
                driver = new ChromeDriver(options);
        }

        return driver;
    }
}

