package ucb.edu.bo.bottelegram.bottelegram.bl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ucb.edu.bo.bottelegram.bottelegram.model.AuthModel;
import ucb.edu.bo.bottelegram.bottelegram.model.MessageReportModel;
import ucb.edu.bo.bottelegram.bottelegram.rest.RestApiController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BotBl {
    final
    RestApiController restApiController;

    private String auth = "";

    public BotBl(RestApiController restApiController) {
        this.restApiController = restApiController;
    }


    public List<String> processUpdate(Update update) {
        List<String> chatResponse = new ArrayList<>();
        AuthModel authModel = restApiController.authenticateApi();
        auth = authModel.getAuthentication();
        log.info("AUTHHH " + auth);

        continueChat(update, chatResponse);
        return chatResponse;
    }
//
    private void continueChat(Update update, List<String> chatResponse) {
        String message = update.getMessage().getText();
        log.info("SECOND AUTHHH " + auth);
        MessageReportModel messageReportModel = restApiController.getAllDataApi(separateWhiteSpace(message), auth);

        chatResponse.add(concatDataCases(
                messageReportModel.getPlace(),
                messageReportModel.getTotalCases(),
                messageReportModel.getTotalDeath(),
                messageReportModel.getTotalRecovered(),
                messageReportModel.getTotalActiveCases(),
                messageReportModel.getTotalCasesToday(),
                messageReportModel.getTotalDeathToday()
        ));


    }
//
    private String concatDataCases(String location, Integer totalCases, Integer deaths, Integer recovered, Integer activeCase, Integer todayCases, Integer todayDeaths) {
        return "*"+location+"*"+
                String.format("\n\n*Casos:* %d\n*Decesos:* %d\n*Recuperados:* %d\n*Casos Activos:* %d\n\n*Casos de Hoy:* %d\n*Muertes de Hoy:* %d",
                        totalCases, deaths, recovered, activeCase, todayCases, todayDeaths);
    }

    private Map<String, String> separateWhiteSpace(String temp) {
        Map<String, String> stringMap = new HashMap<>();
        int indexSpace = temp.indexOf(' ');
        if (indexSpace == -1) {
            stringMap.put("starMessage", temp);
            stringMap.put("department", "Bolivia");
            stringMap.put("isDepartment", "NO");
        } else {
            String startMessage = temp.substring(0, indexSpace);
            String department = temp.substring(indexSpace + 1);

            stringMap.put("starMessage", startMessage);
            stringMap.put("department", department);
            stringMap.put("isDepartment", "SI");
        }

        return stringMap;
    }
}
