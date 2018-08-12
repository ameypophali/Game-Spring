package com.ap.game.chess.user;

import com.ap.game.chess.exception.user.UserAlreadyHasRoleException;
import com.ap.game.chess.exception.user.UserDoesNotHaveRole;
import com.ap.game.chess.role.Role;
import lombok.*;
//import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Builder
    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        //hashAndSavePassword(password);
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true)
    @NotNull
    private Long id;

    @NotNull
    @Column(name="Email",unique = true)
    private String email;

    @NotNull
    @Column(name="FirstName")
    private String firstName;

    @NotNull
    @Column(name="LastName")
    private String lastName;

    @NotNull
    @Column(name="Password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

/*    private void hashAndSavePassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }*/

    public void assignRole(Role role){
        if(this.roles.contains(role)){
            throw new UserAlreadyHasRoleException(this.firstName +
                    " already has role of a " + role.getTitle());
        }
        roles.add(role);
    }

    public void unassignRole(Role removeRole){
        Role presentRole =
                this.roles
                .stream()
                .filter(r -> r.getRoleId().equals(removeRole.getRoleId()))
                .findFirst()
                .orElse(null);
        if(presentRole == null){
            throw new UserDoesNotHaveRole(this.firstName +
                    " does not have a role of " + removeRole.getTitle());
        }
        this.roles.remove(removeRole);
    }

}
