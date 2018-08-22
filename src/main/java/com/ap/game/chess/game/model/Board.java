package com.ap.game.chess.game.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="Board")
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true)
    @NotNull
    private Long id;

    @NotNull
    @Column(name="length")
    private Integer length;

    @NotNull
    @Column(name="breadth")
    private Integer breadth;
}