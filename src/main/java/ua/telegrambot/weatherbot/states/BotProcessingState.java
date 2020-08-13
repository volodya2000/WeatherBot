package ua.telegrambot.weatherbot.states;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.telegrambot.weatherbot.botContext.BotContext;
import ua.telegrambot.weatherbot.model.Weather;

import java.io.IOException;

@Component
public class BotProcessingState implements BotState {

    private static BotProcessingState botProcessingState=new BotProcessingState();

    private BotProcessingState(){}


    public static BotProcessingState getInstance()
    {return botProcessingState;}

    @Override
    public SendMessage handle(BotContext botContext, Update update) {

        Long chat_id=update.getMessage().getChatId();
        String cityName=update.getMessage().getText();
        Weather weather;
        if(update.hasMessage()&&update.getMessage().hasText())
        {
            update(botContext);
            try {
                 weather= botContext.getJsonMapper().getWeather(cityName);
            }catch (IOException ex)
            {
                return new SendMessage().setChatId(chat_id).setText("Населений пункт не знайдено :с \n Спробуйте ще раз");
            }
            return new SendMessage().setChatId(update.getMessage().getChatId()).setText(weather.toString());
        }
        return null;
    }

    @Override
    public void update(BotContext botContext) {
        botContext.setCurrentState(BotWaitingState.getInstance());
    }
}
