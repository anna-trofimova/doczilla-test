package com.example.weatherservice;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        String cityName = "Moscow";

        JSONObject weather = client.getWeatherForCity(cityName);

        if (weather == null) {
            System.out.println("Город не найден: " + cityName);
            return;
        }

        System.out.println("Прогноз погоды для города " + cityName + " (24 часа):");
        System.out.println(weather.toString(2));

        WeatherChart.plot(weather);
    }

}
