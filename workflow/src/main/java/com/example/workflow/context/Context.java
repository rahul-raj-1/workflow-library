package com.example.workflow.context;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<String, Object> data = new HashMap<>();

    // Method to add data to the context
    public void put(String key, Object value) {
        data.put(key, value);
    }

    // Method to get data from the context
    public Object get(String key) {
        return data.get(key);
    }
}

