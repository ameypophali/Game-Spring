package com.ap.game.chess.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBody {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
