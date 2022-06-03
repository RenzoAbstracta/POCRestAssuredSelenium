package Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class Utils {

    public static String getProperty(String fileName,String propertyName) {
        String propertyValue = null;

        try (InputStream input = new FileInputStream("./src/main/resources/data/"+fileName+".properties")) {
            Properties prop = new Properties();
            prop.load(input);
            propertyValue = prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return propertyValue;
    }

    public static String getEnvironment(){
        String selectedEnvironment = System.getProperty("environment");
        String defaultEnvironment = getProperty("environments","default_environment");
        if(!Arrays.asList(getProperty("environments","availables_environments").split(",")).contains(selectedEnvironment) ||
                "".equals(selectedEnvironment) || selectedEnvironment.equals(null)) {
            return defaultEnvironment;
        } else {
            return selectedEnvironment;
        }
    }

    public static String getBrowser(){
        String selectedBrowser = System.getProperty("browser");
        String defaultBrowser = getProperty("environments", "default_browser");

        if(!Arrays.asList(getProperty("environments","availables_browsers").split(",")).contains(selectedBrowser) ||
                "".equals(selectedBrowser) || selectedBrowser.equals(null)) {
            return defaultBrowser;
        } else {
            return selectedBrowser;
        }
    }

    public static Boolean isHeadless(){
        String isHeadless = System.getProperty("headless");
        if(isHeadless != null) {
            System.out.println("la concha de la lora");
            switch(isHeadless.toLowerCase()){
                case "true":
                case "t":
                case "1":
                    return true;
                case "false":
                case "f":
                case "0":
                    return false;
                default:
                    return false;
            }
        }
        return false;
    }
}
