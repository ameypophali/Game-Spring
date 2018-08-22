package com.ap.game.chess.user;

import com.ap.game.chess.game.model.Participant;
import com.ap.game.chess.user.exception.UserAlreadyHasRoleException;
import com.ap.game.chess.user.exception.UserDoesNotHaveRole;
import com.ap.game.chess.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    @Builder
    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    Set<Participant> participations = new HashSet<>();

    void assignRole(Role role){
        if(this.roles.contains(role)){
            throw new UserAlreadyHasRoleException(this.firstName +
                    " already has role of " + role.getTitle());
        }
        this.getRoles().add(role);
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