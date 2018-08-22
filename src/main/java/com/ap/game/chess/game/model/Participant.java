package com.ap.game.chess.game.model;

import com.ap.game.chess.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name="Participant")
@AllArgsConstructor
@NoArgsConstructor
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true)
    @NotNull
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name="isWinner")
    private boolean isWinner;

    @NotNull
    @Column(name="hasCurrentTurn")
    private Boolean hasCurrentTurn;

    @NotNull
    @Column(name="Color")
    private Color color;
}
