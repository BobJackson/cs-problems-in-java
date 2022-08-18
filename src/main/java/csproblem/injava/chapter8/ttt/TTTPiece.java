package csproblem.injava.chapter8.ttt;

import csproblem.injava.chapter8.Piece;

public enum TTTPiece implements Piece {
    X, O, E;

    @Override
    public TTTPiece opposite() {
        return switch (this) {
            case X -> O;
            case O -> X;
            default -> E;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case X -> "X";
            case O -> "O";
            case E -> " ";
        };
    }
}
