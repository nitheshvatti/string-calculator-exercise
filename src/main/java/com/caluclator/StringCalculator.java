package com.caluclator;

import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.length() < 2) {
            if (numbers.isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(numbers);
            }
        } else {
            String delimiter = ",";
            if (numbers.startsWith("//")) {
                int delimiterEndIndex = numbers.indexOf("\n");
                if (delimiterEndIndex != -1) {
                    delimiter = numbers.substring(2, delimiterEndIndex);
                    numbers = numbers.substring(delimiterEndIndex + 1);
                } else {
                    throw new IllegalArgumentException("Invalid input format");
                }
            }
            checkIfStringEndsWithDelimiter(numbers, delimiter);
            delimiter = Pattern.quote(delimiter) + "|" + "\n";
            //Splitting the numbers based on delimiters
            String[] numbersList = numbers.split(delimiter);
            //Calculating sum of the numbers fetched after splitting
            return calculateSum(numbersList);
        }
    }

    private int calculateSum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }

    private void checkIfStringEndsWithDelimiter(String numbers, String delimiter) {
        if (numbers.endsWith(delimiter) || numbers.endsWith("\n")) {
            throw new IllegalArgumentException("String is not allowed to end with a separator");
        }
    }
}
