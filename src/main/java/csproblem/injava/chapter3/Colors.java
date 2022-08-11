package csproblem.injava.chapter3;

public enum Colors {
    RED,
    GREEN,
    BLUE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}