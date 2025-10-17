package ru.mephi.malskiy;

import ru.mephi.malskiy.enums.Coordinates;
import ru.mephi.malskiy.service.WeatherService;

import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            WeatherService weatherService = new WeatherService();

            while (true) {
                System.out.println("Введите код города или EX что бы выйти");
                System.out.println("Доступные города: Москва(MSC), Санкт-Петербург(SPB)");
                System.out.print("> ");
                String command = scanner.nextLine().trim().toUpperCase();

                if (command.equals("EX")) {
                    break;
                }

                System.out.println("Введите количество дней в прогнозе, включая текущий");
                int lim = scanner.nextInt();
                scanner.nextLine();

                switch (command){
                    case "MSC":
                        weatherService.getWeather(Coordinates.MSK.getLocation(), lim);
                        break;
                    case"SPB":
                        weatherService.getWeather(Coordinates.SPB.getLocation(), lim);
                        break;
                    default:
                        System.out.println("Сервис пока не предоставляет информацию по данному городу");
                }
            }
        } catch (Exception e){
            System.out.println("Ошибка работы программы " + e.getMessage());
            e.printStackTrace();
        }
    }
}
