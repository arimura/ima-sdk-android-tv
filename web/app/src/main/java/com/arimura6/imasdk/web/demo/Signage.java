package com.arimura6.imasdk.web.demo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

public class Signage implements Parcelable {
    public static final String INTENT_EXTRA_KEY = "signage";
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

    public String getJSEndpoints() {
        //return ["hoge", "fuga"]
        StringBuilder sb = new StringBuilder("[");
        for (String endpoint : endpoints) {
            sb.append("\"").append(endpoint).append("\",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public String getLabel(){
        return id + ": " + name;
    }

    protected Signage(Parcel in) {
        id = in.readString();
        name = in.readString();
        endpoints = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeStringList(endpoints);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Signage> CREATOR = new Creator<Signage>() {
        @Override
        public Signage createFromParcel(Parcel in) {
            return new Signage(in);
        }

        @Override
        public Signage[] newArray(int size) {
            return new Signage[size];
        }
    };
}
