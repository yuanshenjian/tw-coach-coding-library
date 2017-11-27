package com.thoughtworks.star.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

public final class StringUtils {
    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    public static String writeObjectAsJsonString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Parse object to json error.");
        }
    }

    public static <T> T readJsonStringAsObject(String jsonValue, Class<T> clazz) {
        if (!hasText(jsonValue)) {
            return null;
        }
        try {
            return new ObjectMapper().readValue(jsonValue, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Read json source error.");
        }
    }

    public static boolean hasText(String s) {
        return org.springframework.util.StringUtils.hasText(s);
    }
}
