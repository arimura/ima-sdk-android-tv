package com.arimura6.imasdk.web.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class BuildingList {
    private List<Building> buildings;

    // Constructor for an empty BuildingList
    public BuildingList() {
        this.buildings = new ArrayList<>();
    }

    // Constructor that parses JSON string
    public BuildingList(String json) {
        this.buildings = parseBuildings(json);
    }

    // Getters and Setters
    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    // Static method removed. Instance method for parsing buildings from JSON string
    private List<Building> parseBuildings(String json) {
        List<Building> parsedBuildings = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray buildingsArray = jsonObject.getJSONArray("buildings");

            for (int i = 0; i < buildingsArray.length(); i++) {
                JSONObject buildingObject = buildingsArray.getJSONObject(i);
                Building building = new Building(
                        buildingObject.getInt("id"),
                        buildingObject.getString("name")
                );
                parsedBuildings.add(building);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In a real application, consider a more robust error handling strategy.
        }
        return parsedBuildings;
    }

    // Building class nested inside BuildingList for organization
    public static class Building {
        private int id;
        private String name;

        // Constructor
        public Building(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
