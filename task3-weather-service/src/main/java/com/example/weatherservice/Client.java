package com.example.weatherservice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Client {
    private final String GEOCODING_URL = "https://geocoding-api.open-meteo.com/v1/search?name=%s";
    private final HttpClient client;

    public Client(){
        client = HttpClient.newHttpClient();
    }

    public String findCity(String city) throws IOException, InterruptedException {
        String url = String.format(GEOCODING_URL, city);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }


}
