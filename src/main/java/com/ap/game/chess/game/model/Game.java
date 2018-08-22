package com.ap.game.chess.game.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="Game")
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true)
    @NotNull
    private Long id;

    @NotNull
    @Column(name="Created")
    private Timestamp createdTimeStamp;

    @Column
    private Timestamp endTimeStamp;

    @NotNull
    @Column(name = "Status")
    private GameStatus status;

    @NotNull
    @Column(name = "Inactive_Since")
    private Timestamp inactive_since;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game", orphanRemoval = true)
    private Set<Participant> participants = new HashSet<>();
}
