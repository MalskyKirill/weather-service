package ru.mephi.malskiy.service;

import com.google.gson.*;
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
    public void getWeather(String coordinates, int lim) {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.weather.yandex.ru/v2/forecast?" + coordinates + "&limit=" + lim))
            .header("X-Yandex-Weather-Key", ApiConstants.YANDEX_API_WEATHER_KEY)
            .build();

        try {

            // получили json от сервиса
            HttpResponse<String> response =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            // отфарматировали json
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = gson.toJson(jsonObject);
            System.out.println("Весь ответ от сервера: ");
            System.out.println(prettyJson);

            // взяли и json текущую температуру
            JsonObject fact = jsonObject.get("fact").getAsJsonObject();
            int temp = fact.get("temp").getAsInt();
            System.out.println("Текущая температура: " + temp + "°C");

            // подсчет средней температуры за кол-во (lim) суток
            JsonArray forecasts = jsonObject.getAsJsonArray("forecasts");
            int sum = 0;
            for (JsonElement el : forecasts) {
                JsonObject forecast = el.getAsJsonObject();
                JsonObject parts = forecast.getAsJsonObject("parts");
                JsonObject day = parts.getAsJsonObject("day");
                int tempAvg = day.get("temp_avg").getAsInt();
                sum += tempAvg;
            }

            double avg = (double) sum / lim;
            System.out.printf("Средняя температура за %d дней: %.1f°C%n", lim, avg);



        } catch (IOException | InterruptedException e) {
            System.out.println("Ошибка при обращении к API");
            System.out.println(e.getMessage());
        }

    }
}
