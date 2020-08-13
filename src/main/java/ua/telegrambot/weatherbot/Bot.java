package ua.telegrambot.weatherbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.telegrambot.weatherbot.botContext.BotContext;
import ua.telegrambot.weatherbot.states.BotHelp;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:bot.properties")
public class Bot extends TelegramLongPollingBot {

    private  String botUserName;

    private  String token;

    private BotContext botContext;

    public Bot()
    {}

    @Autowired
    public Bot (@Value("${bot.name}") String name,@Value("${bot.token}") String token,BotContext botContext)
    {
        this.botUserName = name;
        this.token=token;
        this.botContext=botContext;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String text=update.getMessage().getText();
        if(update.hasMessage()&&update.getMessage().hasText())
        {
            if(text.equals("Допомога"))
            {
                botContext.setCurrentState(new BotHelp("Fa"));
            }
            try {
                execute(botContext.handle(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }


    private ReplyKeyboardMarkup createKeyboard()
    {
        KeyboardRow keyboardRow=new KeyboardRow();
        keyboardRow.add("Допомога");
        List<KeyboardRow>keyboardRowList=new ArrayList<>();
        keyboardRowList.add(keyboardRow);
        return new ReplyKeyboardMarkup().setKeyboard(keyboardRowList).setOneTimeKeyboard(true);
    }

}
