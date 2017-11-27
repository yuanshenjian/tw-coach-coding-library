package com.thoughtworks.star.firstproject;

public class LetterContainer {
    private final String letter;
    private final int count;

    public LetterContainer(String letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    public String getLetter() {
        return letter;
    }

    public int getCount() {
        return count;
    }

}
