package com.example.workflow.dmn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGeneration {

	public static void main(String[] args) {
		List<FieldDetails> fieldDetailsList = FeelExpressionParser.populateFieldDetailsList();
		Map<FieldDetails, List<Object>> testDataMap = generateTestData(fieldDetailsList, 50);

		CSVWriter.writeTestDataToCSV(testDataMap);
		System.out.println("Test data has been written to " + CSVWriter.CSV_FILE_PATH);
	}

	public static Map<FieldDetails, List<Object>> generateTestData(List<FieldDetails> fieldDetailsList,
			int numTestData) {
		Map<FieldDetails, List<Object>> testDataMap = new HashMap<>();
		Random random = new Random();

		for (FieldDetails fieldDetails : fieldDetailsList) {
			String dataType = fieldDetails.getDataType();
			List<Object> testData = new ArrayList<>();

			for (int i = 0; i < numTestData; i++) {
				Object value = null;
				if (dataType.equalsIgnoreCase("number")) {
					List<Object> generatedNumbers = fieldDetails.getGeneratedBoundaryData();
					
					int randomIndex = random.nextInt(generatedNumbers.size());
					value = generatedNumbers.get(randomIndex);
				} else if (dataType.equalsIgnoreCase("boolean")) {
					value = random.nextBoolean();
				} else if (dataType.equalsIgnoreCase("string")) {
					value = generateRandomString();
				} else if (dataType.equalsIgnoreCase("localdate")) {
					value = generateRandomLocalDate();
				}

				testData.add(value);
			}

			testDataMap.put(fieldDetails, testData);
		}

		return testDataMap;
	}

	private static String generateRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	private static LocalDate generateRandomLocalDate() {
		LocalDate startDate = LocalDate.of(2023, 1, 1);
		LocalDate endDate = LocalDate.now();
		long startEpochDay = startDate.toEpochDay();
		long endEpochDay = endDate.toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

		return LocalDate.ofEpochDay(randomDay);
	}
}
