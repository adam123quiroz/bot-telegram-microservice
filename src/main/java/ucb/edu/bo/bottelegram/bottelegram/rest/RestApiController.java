package ucb.edu.bo.bottelegram.bottelegram.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import ucb.edu.bo.bottelegram.bottelegram.model.MessageReportModel;
import ucb.edu.bo.bottelegram.bottelegram.model.MessageUser;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RestApiController {
    public MessageReportModel getAllDataApi(Map<String, String> map) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/bot-telegram";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        MessageUser messageUser = new MessageUser();
        messageUser.setStartMessage(map.get("starMessage"));
        messageUser.setPlace(map.get("department"));
        messageUser.setIsDepartment(map.get("isDepartment"));
        HttpEntity<MessageUser> request1 = new HttpEntity<>(messageUser);
        ResponseEntity<MessageReportModel> response = restTemplate.postForEntity(url, request1,MessageReportModel.class);
        log.info(Objects.requireNonNull(response.getBody()).toString());
        return response.getBody();

        //        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/login";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        Map<String, Object> map = new HashMap<>();
//        map.put("username", "Admin");
//        map.put("password", "Admin123");
//        HttpEntity<Map<String, Object>> request1 = new HttpEntity<>(map);
//        ResponseEntity<AuthModel> response = restTemplate.postForEntity( url, request1 , AuthModel.class );
//        log.info(Objects.requireNonNull(response.getBody()).toString());
    }
}
