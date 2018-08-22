package com.ap.game.chess.role;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="Role")
@Builder
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
    private String title;

    @NonNull
    @Column(name="Created")
    private Timestamp created;
}
