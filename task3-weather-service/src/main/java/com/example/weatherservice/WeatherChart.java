package com.example.weatherservice;

import org.json.JSONArray;
import org.json.JSONObject;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;

import java.util.ArrayList;
import java.util.List;

public class WeatherChart {
    public static void plot(JSONObject weatherJson) {
        JSONObject hourly = weatherJson.getJSONObject("hourly");
        JSONArray tempArray = hourly.getJSONArray("temperature_2m");
        JSONArray timeArray = hourly.getJSONArray("time");

        List<String> hours = new ArrayList<>();
        List<Double> temps = new ArrayList<>();

        for (int i = 0; i < tempArray.length(); i++) {
            String time = timeArray.getString(i);
            hours.add(time.substring(11, 13) + ":00");
            temps.add(tempArray.getDouble(i));
        }

        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Температура по часам для " + weatherJson.getString("timezone"))
                .xAxisTitle("Время")
                .yAxisTitle("Температура (°C)")
                .build();

        chart.addSeries("Температура", hours, temps);

        new SwingWrapper<>(chart).displayChart();
    }
}
