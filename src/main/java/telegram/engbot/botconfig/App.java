package telegram.engbot.botconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component @RequiredArgsConstructor
public class App {
    private final Bot bot;

    public void  run() {
        TelegramBotsApi telegramBotsApi = null;
        System.out.println(bot.getBotUsername());
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }



    };

}