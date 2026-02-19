package com.example.weatherservice;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws Exception {
        Client client = new Client();

        String cityName = args.length > 0 ? args[0] : "Berlin";

        JSONObject city = client.findCity(cityName);

        if (city == null) {
            System.out.println("Город не найден: " + cityName);
            return;
        }

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
