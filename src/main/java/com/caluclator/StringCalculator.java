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
            //TODO : Implement for numbers value greater than 2
            return 0;
        }
    }
}