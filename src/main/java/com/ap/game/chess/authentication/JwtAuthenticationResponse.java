package com.ap.game.chess.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String token;
    private String tokenType = "Bearer";

    JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
