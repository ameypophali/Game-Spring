package com.ap.game.chess.role;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="Role")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id", unique=true)
    @NotNull
    private Integer roleId;

    @NotNull
    @Column(name="Role",unique = true)
    private String role;

    @NonNull
    @Column(name="Created",unique = true)
    private Timestamp created;

}
