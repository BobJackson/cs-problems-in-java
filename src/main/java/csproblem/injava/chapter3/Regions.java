package csproblem.injava.chapter3;

public enum Regions {
    WESTERN_AUSTRALIA("Western Australia"),
    NORTHERN_TERRITORY("Northern Territory"),
    SOUTH_AUSTRALIA("South Australia"),
    QUEENSLAND("Queensland"),
    NEW_SOUTH_WALES("New South Wales"),
    VICTORIA("Victoria"),
    TASMANIA("Tasmania");

    private final String displayName;

    Regions(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}