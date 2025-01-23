package src.state;

public enum StareReclamatie {
    INREGISTRATA("INREGISTRATA"),
    INANALIZA("IN ANALIZA"),
    SOLUTIONATA("SOLUTIONATA");

    private final String descriereStare;

    StareReclamatie(String descriereStare) {
        this.descriereStare = descriereStare;
    }

    public String getDescriereStare() {
        return descriereStare;
    }
}
