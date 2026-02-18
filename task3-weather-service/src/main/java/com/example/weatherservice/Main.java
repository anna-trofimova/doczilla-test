package com.example.weatherservice;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws Exception {
        Client client = new Client();

        String cityName = "Berlin";

        JSONObject weather = client.getWeatherForCity(cityName);

        if (weather == null) {
            System.out.println("Город не найден: " + cityName);
            return;
        }

        System.out.println("Прогноз погоды для города " + cityName + " (24 часа):");
        System.out.println(weather.toString(2));

        JSONObject weather2 = client.getWeatherForCity(cityName);
        System.out.println("Второй вызов (из Redis):");
        System.out.println(weather2.toString(2));
    }



    }
