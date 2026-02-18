package com.example.weatherservice;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws Exception {
        Client client = new Client();

        String cityName = "Berlin";
        JSONObject city = client.findCity(cityName);

        if (city == null) {
            System.out.println("Город не найден: " + cityName);
            return;
        }

        // Print city information
        System.out.println("Город найден:");
        System.out.println(city.toString(2));

        double lat = Double.parseDouble(city.get("latitude").toString());
        double lon = Double.parseDouble(city.get("longitude").toString());


        System.out.println("Используем координаты: Latitude = " + lat + ", Longitude = " + lon);

        // Get weather
        JSONObject weather = client.getWeather(lat, lon);

        System.out.println("Прогноз погоды (24 часа):");
        System.out.println(weather.toString(2));

    }
}
