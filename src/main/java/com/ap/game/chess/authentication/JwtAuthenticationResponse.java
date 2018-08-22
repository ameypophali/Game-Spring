package com.ap.game.chess.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class JwtAuthenticationResponse {
    private String token;
    private String tokenType;
    private Long expiry;

    public JwtAuthenticationResponse(String token, Long expiry) {
        this.token = token;
        this.expiry = expiry;
        this.tokenType = "Bearer";
    }
}