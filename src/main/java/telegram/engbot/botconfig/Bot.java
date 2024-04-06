package telegram.engbot.botconfig;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.engbot.service.MessageHandler;
import telegram.engbot.service.MessageHandlerImpl;

@Component
public class Bot extends TelegramLongPollingBot {
    //создаем две константы, присваиваем им значения токена и имя бота соответсвтенно
    //вместо звездочек подставляйте свои данные
    final private String BOT_TOKEN = "7048926802:AAGtWCt0hatBcR2H7iSl97GH6WfPn1QRUc8";
    private boolean flagInGame = false;
    final private String BOT_NAME = "eng_helper_super_bot";
    MessageHandler messageHandler;
    Bot(MessageHandler messageHandler){
        this.messageHandler = messageHandler;
    }
    //Storage storage;

//    Bot()
//    {
//        storage = new Storage();
//    }






    @Override
    public void onUpdateReceived(Update update) {


        try{

            if(update.hasMessage() && update.getMessage().hasText())
            {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                String textMessage = inMess.getText();
                String response ;
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                if(flagInGame){
                    response =startGame(update);
                }
                else {
                    //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                    System.out.println(inMess);
                    System.out.println(flagInGame);
                    if (textMessage.equals("/startgame")) {
                        flagInGame = true;
                        response = startGame(update);
                    } else if (textMessage.equals("/stopgame")) {
                        flagInGame = false;
                        response = stopGame();
                    } else
                        response = "Будете играть?";
                }
                    //Создаем объект класса SendMessage - наш будущий ответ пользователю
                    SendMessage outMess = new SendMessage();

                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);

                outMess.setText(response);

                //Отправка в чат
                execute(outMess);

            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public String startGame(Update update){
       if(update.getMessage().getText().equals("/stopgame")){
           flagInGame=false;
           return "вы вышли из игры";
       };
  return "я в режиме игры";
    }

    public String stopGame(){
return "Игра закончена";
    }

    @Override
    public String getBotUsername() {
        return null;
    }
}