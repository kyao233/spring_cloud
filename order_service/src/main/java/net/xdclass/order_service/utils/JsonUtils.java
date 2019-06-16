package net.xdclass.order_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode str2JsonNode(String str) {
        try {
            return objectMapper.readTree(str);
        } catch (IOException e) {
            throw new IllegalStateException("Json parse fail exception.");
        }
    }

}
