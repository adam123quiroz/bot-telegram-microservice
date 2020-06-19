package ucb.edu.bo.bottelegram.bottelegram.model;

import lombok.Data;

@Data
public class AuthModel {
    private String refresh;
    private String message;
    private String authentication;
}
