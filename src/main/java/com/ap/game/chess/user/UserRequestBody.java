package com.ap.game.chess.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestBody {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
