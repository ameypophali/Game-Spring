package com.ap.game.chess.game.model;

import lombok.Getter;

@Getter
public enum Color {
    BLACK("Black"),
    WHITE("White");

    private String color;

    Color(String color){
        this.color = color;
    }
}