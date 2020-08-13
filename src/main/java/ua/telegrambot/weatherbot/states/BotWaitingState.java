package ua.telegrambot.weatherbot.states;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.telegrambot.weatherbot.botContext.BotContext;

import java.util.logging.Logger;

@Component
public class BotWaitingState implements BotState {

    private static BotWaitingState botWaitingState=new BotWaitingState();

    private BotWaitingState(){}

    public static BotWaitingState getInstance()
    {return botWaitingState;}

    @Override
    public SendMessage handle(BotContext botContext, Update update) {
         if(botContext.getCurrentState() instanceof BotWaitingState)
        {

            update(botContext);
           return botContext.handle(update);
        }
        return null;
    }

    @Override
    public void update(BotContext botContext) {
        botContext.setCurrentState( BotProcessingState.getInstance());
    }
}
