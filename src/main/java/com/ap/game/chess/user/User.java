package com.ap.game.chess.user;

import com.ap.game.chess.role.Role;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Builder
    public User(String username, String password) {
        this.username = username;
        hashAndSavePassword(password);
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true)
    @NotNull
    private Long userId;

    @NotNull
    @Column(name="Username",unique = true)
    private String username;

    @NotNull
    @Column(name="Password")
    private String password;

    @OneToMany
    @JoinTable(name = "User_Role",
                joinColumns = @JoinColumn(name = "User_id", referencedColumnName = "ID"),
                inverseJoinColumns = @JoinColumn(name = "Role_id", referencedColumnName = "ID"))
    private List<Role> role = new ArrayList<>();

    private void hashAndSavePassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
