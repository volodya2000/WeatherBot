package ua.telegrambot.weatherbot.botContext;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.telegrambot.weatherbot.model.JsonMapper;
import ua.telegrambot.weatherbot.states.BotState;
import ua.telegrambot.weatherbot.states.BotWaitingState;

@Component
@Getter
@Setter
public class BotContext {

    private BotState currentState;

    private JsonMapper jsonMapper;

    public BotContext( ){}

    @Autowired
    public BotContext(@Qualifier("botWaitingState") BotState botState, JsonMapper jsonMapper)
    {
        this.currentState=botState;
        this.jsonMapper=jsonMapper;
    }

    public BotState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(BotState currentState) {
        this.currentState = currentState;
    }

    public SendMessage handle(Update update)
    {
      return  currentState.handle(this,update);
    }
}
