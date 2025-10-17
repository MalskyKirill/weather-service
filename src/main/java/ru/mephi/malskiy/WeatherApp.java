package ru.mephi.malskiy;

import ru.mephi.malskiy.enums.Coordinates;
import ru.mephi.malskiy.service.WeatherService;

import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            WeatherService weatherService = new WeatherService();

            while (true) {
                System.out.println("Введите код города или ex");
                System.out.println("Доступные города: Москва(MSC), Санкт-Петербург(SPB)");

                String command = scanner.nextLine().trim();
                if (command.equals("ex")) {
                    break;
                }

                switch (command){
                    case "MSC":
                        weatherService.getWeather(Coordinates.MSK.getLocation());
                        break;
                    case"SPB":
                        weatherService.getWeather(Coordinates.SPB.getLocation());
                        break;
                    default:
                        System.out.println("Сервис пока не предоставляет информацию по данному городу");
                }
            }
        }
    }
}
