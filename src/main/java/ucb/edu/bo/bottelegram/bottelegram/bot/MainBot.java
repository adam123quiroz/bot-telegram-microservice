package ucb.edu.bo.bottelegram.bottelegram.bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.bo.bottelegram.bottelegram.bl.BotBl;


import java.util.*;

@Slf4j
public class MainBot extends TelegramLongPollingBot {
    final private BotBl botBl;

    public MainBot(BotBl botBl) {
        this.botBl = botBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            List<String> messages = botBl.processUpdate(update);
            messages.forEach(s -> {
                // Create a SendMessage object with mandatory fields
                SendMessage message = new SendMessage()
                        .enableMarkdown(true)
                        .setChatId(update.getMessage().getChatId())
                        .setText(s);
                try {
                    this.execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });
        }

//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/login";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        UserModel userModel = new UserModel();
//        userModel.setUsername("Admin");
//        userModel.setPassword("Admin123");
//        HttpEntity<UserModel> request1 = new HttpEntity<>(userModel);
//        ResponseEntity<String> response = restTemplate.postForEntity( url, request1 , String.class );
    }

    @Override
    public String getBotUsername() {
        return "CovidBol_bot";
    }

    @Override
    public String getBotToken() {
        return "1203240683:AAGmVpY5pbALa8u5RqMujHnlYBqOFk9xkS4";
    }
}
