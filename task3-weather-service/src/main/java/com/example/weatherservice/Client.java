package com.example.weatherservice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Client {
    private  final  String BASE_URL= "https://geocoding-api.open-meteo.com/v1/search?name=Berlin";
    private final HttpClient client;

    public Client(){
        client = HttpClient.newHttpClient();
    }

    public String findAll() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }


}
