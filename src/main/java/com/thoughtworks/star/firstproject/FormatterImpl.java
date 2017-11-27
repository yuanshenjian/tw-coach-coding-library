package com.thoughtworks.star.firstproject;

import java.util.List;
import java.util.stream.Collectors;

public class FormatterImpl implements Formatter {

    @Override
    public String format(List<LetterContainer> source) {
        return source.stream().map(container -> {
            StringBuilder sb = new StringBuilder();
            sb.append(container.getCount());
            sb.append("(");
            sb.append(container.getLetter());
            sb.append(")");
            return sb.toString();
        }).collect(Collectors.joining(" < "));
    }
}
