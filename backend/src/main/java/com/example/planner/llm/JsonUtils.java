package com.example.planner.llm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode parse(String json) {
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON from LLM", e);
        }
    }

    public static String normalize(String json) {
        try {
            return mapper.writeValueAsString(mapper.readTree(json));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON from LLM", e);
        }
    }

    public static String pretty(String json) {
        try {
            Object obj = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);
        } catch (Exception e) {
            return json; // fallback (never crash logging)
        }
    }
}
