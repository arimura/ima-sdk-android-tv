package com.arimura6.imasdk.web.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class EndpointList {
    private List<String> endpoints;

    // Constructor for an empty EndpointList
    public EndpointList() {
        this.endpoints = new ArrayList<>();
    }

    // Constructor that parses JSON string
    public EndpointList(String json) {
        this.endpoints = parseEndpoints(json);
    }

    // Getters and Setters
    public List<String> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    // Method for parsing endpoints from JSON string
    private List<String> parseEndpoints(String json) {
        List<String> parsedEndpoints = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray endpointsArray = jsonObject.getJSONArray("endpoints");

            for (int i = 0; i < endpointsArray.length(); i++) {
                String endpoint = endpointsArray.getString(i);
                parsedEndpoints.add(endpoint);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In a real application, consider a more robust error handling strategy.
        }
        return parsedEndpoints;
    }
}
