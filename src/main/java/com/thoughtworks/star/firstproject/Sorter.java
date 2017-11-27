package com.thoughtworks.star.firstproject;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class Sorter {

    private final Formatter formatter;

    public Sorter(Formatter printer) {
        this.formatter = printer;
    }

    public List<LetterContainer> countLength(String words) {
        List<String> letters = Arrays.asList(words.split(""));
        Map<String, Integer> counter = new HashMap<>();
        letters.forEach(letter -> counter.put(letter, (counter.get(letter) == null ? 1 : counter.get(letter) + 1)));
        return counter.entrySet().stream()
                .map(entry -> new LetterContainer(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(LetterContainer::getLetter))
                .collect(Collectors.toList());
    }

    public String present(String words) {
        return formatter.format(countLength(words));
    }
}
