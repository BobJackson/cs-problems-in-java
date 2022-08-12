package csproblem.injava.chapter4;

public enum City {
    SEATTLE("Seattle"),
    SAN_FRANCISCO("San Francisco"),
    LOS_ANGELES("Los Angeles"),
    RIVERSIDE("Riverside"),
    PHOENIX("Phoenix"),
    CHICAGO("Chicago"),
    BOSTON("Boston"),
    NEW_YORK("New York"),
    ATLANTA("Atlanta"),
    MIAMI("Miami"),
    DALLAS("Dallas"),
    HOUSTON("Houston"),
    DETROIT("Detroit"),
    PHILADELPHIA("Philadelphia"),
    WASHINGTON("Washington");

    private final String displayName;

    City(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
