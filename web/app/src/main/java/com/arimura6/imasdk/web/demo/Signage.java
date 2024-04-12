package com.arimura6.imasdk.web.demo;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

public class Signage {
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ENDPOINTS = "endpoints";

    private final String id;
    private final String name;
    private final List<String> endpoints;

    public Signage(@NonNull String id, @NonNull Map<String, Object> map){
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("The id must not be null or empty");
        }
        if (!map.containsKey(KEY_NAME) || !(map.get(KEY_NAME) instanceof String)) {
            throw new IllegalArgumentException("The map must contain a valid String for Name");
        }
        if (!map.containsKey(KEY_ENDPOINTS) || !(map.get(KEY_ENDPOINTS) instanceof List)) {
            throw new IllegalArgumentException("The map must contain a valid List for Endpoints");
        }
        this.id = id;
        this.name = (String) map.get(KEY_NAME);
        this.endpoints = (List<String>) map.get(KEY_ENDPOINTS);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }
}
