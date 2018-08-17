package com.ap.game.chess.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleTitle {
    role_admin("ROLE_ADMIN"),
    role_trial("ROLE_TRIAL"),
    role_user("ROLE_USER");

    private final String role;
}
