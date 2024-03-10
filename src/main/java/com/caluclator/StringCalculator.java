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
            return calculateSum(numbersList, delimiter);
        }
    }

    private int calculateSum(String[] numbers, String delimiter) {
        int sum = 0;
        StringBuilder negativeNumbers = new StringBuilder();
        StringBuilder errorString = new StringBuilder();
        for (String number : numbers) {
            try {
                int integerNumber = Integer.parseInt(number);
                if (integerNumber < 0){
                    appendNegativeNumberToString(negativeNumbers, number);
                }
                else {
                    sum += integerNumber;
                }
            } catch (Exception e){
                String errors = getAllErrorsInTheString(number, delimiter);
                errorString.append(errors);
            }
        }
        if (!negativeNumbers.toString().isEmpty()) {
            errorString.append("Negative number(s) not allowed: ").append(negativeNumbers);
        }
        if (!errorString.toString().isEmpty()) {
            throw new IllegalArgumentException(String.valueOf(errorString));
        }
        return sum;
    }

    private void checkIfStringEndsWithDelimiter(String numbers, String delimiter) {
        if (numbers.endsWith(delimiter) || numbers.endsWith("\n")) {
            throw new IllegalArgumentException("String is not allowed to end with a separator");
        }
    }

    private String getAllErrorsInTheString(String invalidString, String delimiter){
        delimiter = delimiter.substring(2, delimiter.length() - 4); //removing \n
        StringBuilder currentString = new StringBuilder();
        StringBuilder errorString =  new StringBuilder();
        for (int i = 0; i < invalidString.length(); i++) {
            char ch = invalidString.charAt(i);
            if (Character.isDigit(ch) || ch == '-' ) {
                if (!currentString.toString().isEmpty()) {
                    errorString.append(delimiter).append(" expected but ").append(currentString)
                            .append(" found").append("\n");
                }
                currentString.setLength(0);
            } else {
                currentString.append(ch);
            }
        }
        return errorString.toString();
    }


    private void appendNegativeNumberToString(StringBuilder negativeNumbers, String number){
        if (negativeNumbers.toString().isEmpty())
            negativeNumbers.append(number);
        else
            negativeNumbers.append(", ").append(number);
    }
}
