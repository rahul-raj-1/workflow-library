package com.example.workflow.dmn;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CSVWriter {

	 static final String CSV_FILE_PATH = "E:/testdata.csv";

    public static void writeTestDataToCSV(Map<FieldDetails, List<Object>> testDataMap) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            // Write column headers
            for (FieldDetails fieldDetails : testDataMap.keySet()) {
                writer.append(fieldDetails.getFieldName()).append(",");
            }
            writer.append("\n");

            // Write test data rows
            int numTestData = testDataMap.values().stream().findFirst().orElseThrow().size();
            for (int i = 0; i < numTestData; i++) {
                for (FieldDetails fieldDetails : testDataMap.keySet()) {
                    List<Object> testData = testDataMap.get(fieldDetails);
                    Object value = testData.get(i);
                    writer.append(value.toString()).append(",");
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
