package src.state;

public enum State {
    PRELUATA("PRELUATA"),
    PREGATITA("PREGATITA"),
    PLATITA("PLATITA"),
    ONORATA("ONORATA");

    private final String denumire;
    State(String denumire) {
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }
}
