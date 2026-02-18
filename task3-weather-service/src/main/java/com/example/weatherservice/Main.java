package com.example.weatherservice;

public class Main {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        System.out.println(client.findCity("Berlin"));
        System.out.println(client.findCity("Moscow"));
    }
}
