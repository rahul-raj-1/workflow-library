package com.example.workflow.dmn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeelExpressionParser2 {

    private static final String NUMBER_PATTERN = "([-+]?\\d+(?:\\.\\d+)?)";

    public static Set<Object> extractDataFromExpression(Set<String> expressions, String dataType) {
        Set<Object> data = new HashSet<>();

        for (String expression : expressions) {
            switch (dataType) {
                case "number":
                    extractNumbersFromExpression(expression, data);
                    break;
                case "string":
                    extractStringsFromExpression(expression, data);
                    break;
                case "date":
                    extractDatesFromExpression(expression, data);
                    break;
                case "boolean":
                    extractBooleansFromExpression(expression, data);
                    break;
            }
        }

        return data;
    }

    private static void extractNumbersFromExpression(String expression, Set<Object> data) {
        Matcher matcher = Pattern.compile(NUMBER_PATTERN).matcher(expression);
        while (matcher.find()) {
            String numberStr = matcher.group(1);
            double number = Double.parseDouble(numberStr);
            data.add(number);
        }
    }

    private static void extractStringsFromExpression(String expression, Set<Object> data) {
        data.add(expression);
    }

    private static void extractDatesFromExpression(String expression, Set<Object> data) {
        LocalDate date = LocalDate.parse(expression);
        data.add(date);
    }

    private static void extractBooleansFromExpression(String expression, Set<Object> data) {
        boolean value = Boolean.parseBoolean(expression);
        data.add(value);
    }

    public static List<FieldDetails> populateFieldDetailsList() {
        List<FieldDetails> fieldDetailsList = new ArrayList<>();

        // Field 1
        String fieldName1 = "Field1";
        String dataType1 = "number";
        Set<String> boundaryConditions1 = new HashSet<>();
        boundaryConditions1.add("[18..25]");
        boundaryConditions1.add(">25");

        FieldDetails field1 = new FieldDetails(fieldName1, dataType1, boundaryConditions1);
        field1.setGeneratedBoundaryData(new ArrayList<>(extractDataFromExpression(field1.getFeelExpressions(), dataType1)));
        fieldDetailsList.add(field1);

        // Field 2
        String fieldName2 = "Field2";
        String dataType2 = "string";
        Set<String> boundaryConditions2 = new HashSet<>();
        boundaryConditions2.add("Value1");
        boundaryConditions2.add("Value2");

        FieldDetails field2 = new FieldDetails(fieldName2, dataType2, boundaryConditions2);
        field2.setGeneratedBoundaryData(new ArrayList<>(extractDataFromExpression(field2.getFeelExpressions(), dataType2)));
        fieldDetailsList.add(field2);

        // Field 3
        String fieldName3 = "Field3";
        String dataType3 = "date";
        Set<String> boundaryConditions3 = new HashSet<>();
        boundaryConditions3.add("2023-06-01");
        boundaryConditions3.add("2023-06-15");

        FieldDetails field3 = new FieldDetails(fieldName3, dataType3, boundaryConditions3);
        field3.setGeneratedBoundaryData(new ArrayList<>(extractDataFromExpression(field3.getFeelExpressions(), dataType3)));
        fieldDetailsList.add(field3);

        // Field 4
        String fieldName4 = "Field4";
        String dataType4 = "boolean";
        Set<String> boundaryConditions4 = new HashSet<>();
        boundaryConditions4.add("true");
        boundaryConditions4.add("false");

        FieldDetails field4 = new FieldDetails(fieldName4, dataType4, boundaryConditions4);
        field4.setGeneratedBoundaryData(new ArrayList<>(extractDataFromExpression(field4.getFeelExpressions(), dataType4)));
        fieldDetailsList.add(field4);

        return fieldDetailsList;
    }

    public static void main(String[] args) {
        List<FieldDetails> fieldDetailsList = populateFieldDetailsList();
    //    Map<FieldDetails, List<Object>> testDataMap = generateTestData(fieldDetailsList, 50);
      //  CSVWriter.writeTestDataToCSV(testDataMap);
    }
}
