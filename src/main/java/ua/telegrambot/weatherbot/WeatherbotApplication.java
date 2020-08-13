package ua.telegrambot.weatherbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class WeatherbotApplication {

    public static void main(String[] args) {


        ApiContextInitializer.init();
        SpringApplication.run(WeatherbotApplication.class, args);

    }
}
