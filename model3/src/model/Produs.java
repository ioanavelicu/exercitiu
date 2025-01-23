package src.model;

public class Produs {
    private int idProdus;
    private String denumire;
    private int pret;

    public Produs(int idProdus, String denumire, int pret) {
        this.idProdus = idProdus;
        this.denumire = denumire;
        this.pret = pret;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "idProdus=" + idProdus +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }
}
