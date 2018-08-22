package com.ap.game.chess.game.model;

import lombok.Getter;

@Getter
public enum PieceType {
    King("KING"),
    Queen("QUEEN"),
    Knight("KNIGHT"),
    Rook("ROOK"),
    Bishop("BISHOP"),
    Pawn("PAWN");

    private String type;

    PieceType(String type){
        this.type = type;
    }
}
