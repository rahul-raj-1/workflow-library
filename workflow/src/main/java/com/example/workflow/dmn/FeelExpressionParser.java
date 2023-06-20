package com.example.workflow.dmn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeelExpressionParser {

    private static final String NUMBER_PATTERN = "([-+]?\\d+(?:\\.\\d+)?)";

    public static Set<Double> extractNumbersFromExpression(Set<String> expressions) {
        Set<Double> numbers = new HashSet<>();

        for (String expression : expressions) {
            // Handle range expressions
            if (expression.contains("..") || expression.contains("...")) {
                String startBoundary = "";
                String endBoundary = "";

                Matcher matcher = Pattern.compile(NUMBER_PATTERN).matcher(expression);
                if (matcher.find()) {
                    startBoundary = matcher.group(1);
                }
                if (matcher.find()) {
                    endBoundary = matcher.group(1);
                }

                if (!startBoundary.isEmpty()) {
                    numbers.add(Double.parseDouble(startBoundary));
                }
                if (!endBoundary.isEmpty()) {
                    numbers.add(Double.parseDouble(endBoundary));
                }
            }
            // Handle list expressions
            else if (expression.contains("[") || expression.contains("]") || expression.contains(",")) {
                Matcher matcher = Pattern.compile(NUMBER_PATTERN).matcher(expression);
                while (matcher.find()) {
                    String numberStr = matcher.group(1);
                    double number = Double.parseDouble(numberStr);
                    numbers.add(number);
                }
            }
            // Handle other expressions
            else {
                Matcher matcher = Pattern.compile(NUMBER_PATTERN).matcher(expression);
                if (matcher.find()) {
                    String numberStr = matcher.group(1);
                    double number = Double.parseDouble(numberStr);
                    numbers.add(number);
                }
            }
        }

        return numbers;
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
        field1.setGeneratedBoundaryData(new ArrayList<>(extractNumbersFromExpression(field1.getFeelExpressions())));
        fieldDetailsList.add(field1);

        // Field 2
        String fieldName2 = "Field2";
        String dataType2 = "number";
        Set<String> boundaryConditions2 = new HashSet<>();
        boundaryConditions2.add("<10");
        boundaryConditions2.add(">=90");
        boundaryConditions2.add("<=500");

        FieldDetails field2 = new FieldDetails(fieldName2, dataType2, boundaryConditions2);
        field2.setGeneratedBoundaryData(new ArrayList<>(extractNumbersFromExpression(field2.getFeelExpressions())));
        fieldDetailsList.add(field2);
        
        String fieldName4 = "Field4";
        String dataType4 = "boolean";
        Set<String> boundaryConditions4 = new HashSet<>();
        boundaryConditions4.add("true");
        boundaryConditions4.add("false");

        FieldDetails field4 = new FieldDetails(fieldName4, dataType4, boundaryConditions4);
        field4.setGeneratedBoundaryData(new ArrayList<>());
        fieldDetailsList.add(field4);


        return fieldDetailsList;
    }
    
 
}
