package telegram.engbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegram.engbot.botconfig.App;
import telegram.engbot.botconfig.Bot;

@SpringBootApplication
public class EngbotApplication {
	@Autowired
   static private App app ;
	public static void main(String[] args) {
		app.run();
	}

}
