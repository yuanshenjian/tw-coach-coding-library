package com.thoughtworks.star.firstproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Calculator {
    public double sum(double number1, double number2) {
        return number1 + number2;
    }

    public int sumEvens(List<Integer> integers) {
        return integers.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(Integer::intValue).sum();
    }

    public Map<String, Integer> sortAndCount(List<String> strings) {
        Collections.sort(strings);
        Map<String, Integer> result = new LinkedHashMap<>();
        strings.forEach(item -> result.put(item, item.length()));
        return result;
    }

    public List<Integer> generateRandomNumbers(int maxOrder) {
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        while (result.size() < maxOrder) {
            int number = random.nextInt(maxOrder) + 1;
            if (result.indexOf(number) == -1) {
                result.add(number);
            }
        }
        return result;
    }
}
