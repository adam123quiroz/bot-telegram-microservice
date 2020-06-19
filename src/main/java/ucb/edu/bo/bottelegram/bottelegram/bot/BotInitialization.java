package ucb.edu.bo.bottelegram.bottelegram.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ucb.edu.bo.bottelegram.bottelegram.bl.BotBl;


import javax.annotation.PostConstruct;

@Component
public class BotInitialization {
    final
    BotBl botBl;

    public BotInitialization(BotBl botBl) {
        this.botBl = botBl;
    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(botBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
