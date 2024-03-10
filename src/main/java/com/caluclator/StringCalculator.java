package com.caluclator;

import java.util.regex.Pattern;

import static com.caluclator.Constants.*;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.length() < 2) {
            return parseForLessThanTwoNumbers(numbers);
        } else {
            String delimiter = DEFAULT_DELIMITER;
            if (numbers.startsWith(DOUBLE_SLASH)) {
                //Updating the delimiter
                int delimiterEndIndex = numbers.indexOf(NEWLINE);
                if (delimiterEndIndex != -1) {
                    delimiter = numbers.substring(2, delimiterEndIndex);
                    numbers = numbers.substring(delimiterEndIndex + 1);
                } else {
                    throw new IllegalArgumentException(INVALID_INPUT_FORMAT);
                }
            }
            checkIfStringEndsWithDelimiter(numbers, delimiter);
            //Updating the delimiter
            delimiter = Pattern.quote(delimiter) + PIPE + NEWLINE;
            //Splitting the numbers based on delimiters
            String[] numbersList = numbers.split(delimiter);
            //Calculating sum of the numbers after splitting
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
                else if (integerNumber <= 1000){
                    sum += integerNumber;
                }
            } catch (Exception e){
                String errors = getAllErrorsInTheString(number, delimiter);
                errorString.append(errors);
            }
        }
        if (!negativeNumbers.toString().isEmpty()) {
            errorString.append(NEGATIVE_NUMBERS_NOT_ALLOWED).append(negativeNumbers);
        }
        if (!errorString.toString().isEmpty()) {
            throw new IllegalArgumentException(String.valueOf(errorString));
        }
        return sum;
    }

    private void checkIfStringEndsWithDelimiter(String numbers, String delimiter) {
        if (numbers.endsWith(delimiter) || numbers.endsWith(NEWLINE)) {
            throw new IllegalArgumentException(NOT_ALLOWED_TO_END_WITH_SEPARATOR);
        }
    }

    private String getAllErrorsInTheString(String invalidString, String delimiter){
        delimiter = delimiter.substring(2, delimiter.length() - 4); //removing \n
        StringBuilder currentNumber = new StringBuilder();
        StringBuilder currentString = new StringBuilder();
        StringBuilder errorString =  new StringBuilder();
        //Iterating through the string, storing numbers in currentNumber and incorrect delimiters in currentString
        for (int i = 0; i < invalidString.length(); i++) {
            char ch = invalidString.charAt(i);
            if (Character.isDigit(ch) || (ch == '-' && currentNumber.toString().isEmpty())) {
                //Adding incorrect delimiter string to error string
                if (!currentString.toString().isEmpty()) {
                    errorString.append(delimiter).append(EXPECTED_BUT).append(currentString)
                            .append(FOUND).append(NEWLINE);
                }
                currentString.setLength(0);
                currentNumber.append(ch);
            } else {
                //If number is negative, adding it to the error string
                if (isNegativeNumber(currentNumber)) {
                    errorString.append(NEGATIVE_NUMBERS_NOT_ALLOWED).append(currentNumber).append(NEWLINE);
                }
                currentNumber.setLength(0);
                currentString.append(ch);
            }
        }
        if (isNegativeNumber(currentNumber)) {
            errorString.append(NEGATIVE_NUMBERS_NOT_ALLOWED).append(currentNumber).append(NEWLINE);
        }
        return errorString.toString();
    }


    private void appendNegativeNumberToString(StringBuilder negativeNumbers, String number){
        if (negativeNumbers.toString().isEmpty())
            negativeNumbers.append(number);
        else
            negativeNumbers.append(SEPARATOR).append(number);
    }

    private boolean isNegativeNumber(StringBuilder number){
        return !number.toString().isEmpty() && Integer.parseInt(String.valueOf(number)) < 0;
    }

    private int parseForLessThanTwoNumbers(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(numbers);
        }
    }
}
