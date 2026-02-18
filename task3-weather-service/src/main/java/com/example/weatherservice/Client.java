package com.example.weatherservice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;



public class Client {
    private final String GEOCODING_URL = "https://geocoding-api.open-meteo.com/v1/search?name=%s";
    private final String WEATHER_URL = "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&hourly=temperature_2m";
    private final HttpClient client;

    public Client(){
        client = HttpClient.newHttpClient();
    }

    // Get city coordination
    public JSONObject findCity(String city) throws IOException, InterruptedException {
        String url = String.format(GEOCODING_URL, city);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());


        // Check for empty results
        JSONArray results = json.optJSONArray("results");
        if (results == null || results.length() == 0) return null;

        return results.getJSONObject(0);
    }

    // Get weather information
    public JSONObject getWeather(double latitude, double longitude) throws IOException, InterruptedException {
        String url = String.format(Locale.US,
                "https://api.open-meteo.com/v1/forecast?latitude=%.6f&longitude=%.6f&hourly=temperature_2m",
                latitude, longitude);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }


}
