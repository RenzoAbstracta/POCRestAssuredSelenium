package DataProviders;

import DataProviders.csv.DefaultCSVDataProvider;
//import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Iterator;

public class DataProviderClass {

    private static String fileName = "new_users.csv";
    private static int[] fileSelectedRows = {};
    private static String dir = "integrations_csv";

    public static void setFileName(String name) { fileName = name; }

    /**
     * Get the full path of a project file by indicating the file's name
     * @param fileName of the project file to get the path from
     * @return full path of project file with fileName
     * @throws NullPointerException if file with name = fileName does not exist
     */
    private static String getPath(String fileName) {
        String path = DataProviderClass.class.getClassLoader().getResource(dir).getPath();
        path = path.replaceAll("bin", "src");
        path = path + "/" + fileName;
        return path;
    }

    public static void setFileRows(int[] rows) { fileSelectedRows = rows; }

    public static void setFilesDirectoryName(String dirName) { dir = dirName; }

    /*@DataProvider(name="data-provider-default-csv")
    public static Iterator<Object[]> getDefaultCSVData() {
        Arrays.sort(fileSelectedRows);
        return DefaultCSVDataProvider.getDataFromFile(getPath(fileName), fileSelectedRows);
    }*/

}
