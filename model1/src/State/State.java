package src.State;

public enum State {
    PRELUATA("PRELUATA"),
    PREGATITA("PREGATITA"),
    PLATITA("PLATITA"),
    PLASATA("PLASATA");

    private final String description;

    State(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
