package com.example.workflow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LexicographicalDateComparison {
    public static void main(String[] args) {
    	
    	String date1Str = "01-02-2023";
    	String date2Str = "02-01-2023";

    	int comparisonResult = date1Str.compareTo(date2Str);

    	if (comparisonResult < 0) {
    	    System.out.println(date1Str + " is earlier than " + date2Str);
    	} else if (comparisonResult > 0) {
    	    System.out.println(date1Str + " is later than " + date2Str);
    	} else {
    	    System.out.println(date1Str + " and " + date2Str + " are the same date");
    	}

    }
}
