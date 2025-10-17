package ru.mephi.malskiy.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.mephi.malskiy.util.ApiConstants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    HttpClient httpClient;
    public WeatherService() {
        this.httpClient = HttpClient.newHttpClient();
    }
    public void getWeather(String coordinates) {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.weather.yandex.ru/v2/forecast?" + coordinates + "&limit=2"))
            .header("X-Yandex-Weather-Key", ApiConstants.YANDEX_API_WEATHER_KEY)
            .build();

        try {
            HttpResponse<String> response =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String prettyJson = gson.toJson(jsonObject);

            System.out.println(prettyJson);

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
