package ua.telegrambot.weatherbot.states;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.telegrambot.weatherbot.botContext.BotContext;

import java.io.IOException;

@Component
@PropertySource("classpath:bot.properties")
@Getter
@Setter
public class BotHelp implements BotState{

    private  String helpText;

    public BotHelp(){}

    public BotHelp(@Value("${bot.help}") String helpText) {
        this.helpText = helpText;
    }

    @Override
    public SendMessage handle(BotContext botContext, Update update) {
        if(update.hasMessage()&&update.getMessage().hasText())
        {
            System.out.println(update.getMessage().getText());
            System.out.println(helpText);
            update(botContext);
            return new SendMessage().setChatId(update.getMessage().getChatId()).setText(helpText);
        }
        return null;
    }

    @Override
    public void update(BotContext botContext) {
        botContext.setCurrentState(BotWaitingState.getInstance());
    }
}
