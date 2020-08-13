package ua.telegrambot.weatherbot.states;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.telegrambot.weatherbot.botContext.BotContext;

public interface BotState {
     SendMessage handle(BotContext botContext, Update update);
     void  update(BotContext botContext);
}
