package ucb.edu.bo.bottelegram.bottelegram.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import ucb.edu.bo.bottelegram.bottelegram.model.AuthModel;
import ucb.edu.bo.bottelegram.bottelegram.model.MessageReportModel;
import ucb.edu.bo.bottelegram.bottelegram.model.MessageUser;
import ucb.edu.bo.bottelegram.bottelegram.model.UserModel;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RestApiController {
    private final RestTemplate restTemplate = new RestTemplate();

    public MessageReportModel getAllDataApi(Map<String, String> map, String auth) {

        String url = "http://localhost:8080/bot-telegram";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token " + auth);
        MessageUser messageUser = new MessageUser();
        messageUser.setStartMessage(map.get("starMessage"));
        messageUser.setPlace(map.get("department"));
        messageUser.setIsDepartment(map.get("isDepartment"));
        HttpEntity<MessageUser> request1 = new HttpEntity<>(messageUser, headers);
        ResponseEntity<MessageReportModel> response = restTemplate.postForEntity(url, request1,MessageReportModel.class);
        log.info(Objects.requireNonNull(response.getBody()).toString());
        return response.getBody();
    }

    public AuthModel authenticateApi() {
        String url = "http://localhost:8080/login";
        UserModel userModel = new UserModel();
        userModel.setUsername("Admin");
        userModel.setPassword("Admin123");
        HttpEntity<UserModel> request1 = new HttpEntity<>(userModel);
        ResponseEntity<AuthModel> response = restTemplate.postForEntity(url, request1,AuthModel.class);
        return response.getBody();
    }
}
