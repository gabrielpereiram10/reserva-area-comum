package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.domain.valueObjects.Token;

import java.util.UUID;

public class AuthHashGeneratorOutput {

    private Token accessToken, refreshToken;
    private String tokenType;

    public AuthHashGeneratorOutput(UUID accessToken, UUID refreshToken, String tokenType) {
        this.accessToken = new Token(accessToken);
        this.refreshToken = new Token(refreshToken);
        this.tokenType = tokenType;
    }

    protected Token getAccessToken() {
        return accessToken;
    }

    protected void setAccessToken(Token accessToken) {
        this.accessToken = accessToken;
    }

    protected Token getRefreshToken() {
        return refreshToken;
    }

    protected void setRefreshToken(Token refreshToken) {
        this.refreshToken = refreshToken;
    }

    protected String getTokenType() {
        return tokenType;
    }

    protected void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
