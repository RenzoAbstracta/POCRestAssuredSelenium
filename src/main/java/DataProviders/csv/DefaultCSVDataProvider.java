package DataProviders.csv;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultCSVDataProvider {

    private static Object[] parseCSVRecordToObjectArray(CSVRecord record) {
        int size = record.size();
        Object[] ret = new Object[size];
        for(int i = 0; i < size; i++) ret[i] = record.get(i);
        return ret;
    }

    /**
     * Get data from a default csv file
     * @param filePath path to the csv file
     * @param selectedRows rows to get from the csv file, get the full file if the array is empty
     * @return Iterator of Object[], which are the selected rows from the csv file
     * @throws IOException if file does not exist, if file is not a default csv file
     * @throws NullPointerException if selectedRows == null
     * @throws Exception if selectedRows is not sorted in ascending order
     */
    public static Iterator<Object[]> getDataFromFile(String filePath, int[] selectedRows) {
            Iterator<CSVRecord> records = getRowsFromFile(filePath, selectedRows);
            return new Iterator<Object[]>() {
                @Override
                public boolean hasNext() {
                    return records.hasNext();
                }
                @Override
                public Object[] next() {
                    return parseCSVRecordToObjectArray(records.next());
                }
            };
    }

    /**
     * Get selected rows from the csv file as an iterator of iterators (CSVRecord)
     * @param filePath path to the csv file
     * @param selectedRows rows to get from the csv file, get the full file if the array is empty
     * @return Iterator of CSVRecord, which are iterators that contains data from the selected rows of the csv file
     * @throws IOException if file does not exist, if file is not a default csv file
     * @throws NullPointerException if selectedRows == null
     * @throws Exception if selectedRows is not sorted in ascending order
     */
    private static Iterator<CSVRecord> getRowsFromFile(String filePath, int[] selectedRows) {
        try {
            Iterator<CSVRecord> records = CSVFormat.DEFAULT.parse(new FileReader(filePath)).iterator();
            if (selectedRows.length > 0) {
                List<CSVRecord> list = new ArrayList<>();
                int j = 0; //records index
                int i = 0; //rows index
                while(records.hasNext() && selectedRows.length > i) {
                    if(j < selectedRows[i]) {
                        j++;
                        records.next();
                    } else if (j > selectedRows[i]) {
                        i++;
                    } else { //j == rows[i]
                        list.add(records.next());
                        j++;
                        i++;
                    }
                }
                records = list.iterator();
            }
            return records;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
