package com.caluclator;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.length() < 2) {
            if (numbers.isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(numbers);
            }
        } else {
            String delimiter = "[,\n]";
            //Splitting the numbers based on delimiters
            String [] numbersList = numbers.split(delimiter);
            //Calculating sum of the numbers fetched after splitting
            return calculateSum(numbersList);
        }
    }

    private int calculateSum(String[] numbers){
        int sum = 0;
        for(String number : numbers){
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}
