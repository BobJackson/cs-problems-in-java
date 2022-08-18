package csproblem.injava.chapter8.c4;

import csproblem.injava.chapter8.Piece;

public enum C4Piece implements Piece {
    B, R, E;

    @Override
    public C4Piece opposite() {
        return switch (this) {
            case B -> R;
            case R -> B;
            default -> E;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case B -> "B";
            case R -> "R";
            case E -> " ";
        };
    }
}
