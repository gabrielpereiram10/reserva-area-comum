package com.gabriel.reservaareacomum.infra.presentation.presenters;

import com.gabriel.reservaareacomum.application.auth.AuthUseCaseOB;

import java.util.HashMap;
import java.util.Map;

public class AuthPresenter {

    public static Map<String, Object> format(AuthUseCaseOB output, String token) {
        String tokenType = "Bearer";
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("type", tokenType);
        tokenData.put("token", token);

        Map<String, Object> data = new HashMap<>();
        data.put("user", output);
        data.put("token", tokenData);
        return data;
    }
}
