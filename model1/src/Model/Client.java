package src.Model;

import src.Observer.IObserver;

public class Client implements IObserver {
    private int id;
    private String nume;

    public Client(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public void primesteUpdateDeLaComanda(Comanda comanda) {
        System.out.println(this.nume + ": Comanda cu numarul " + comanda.getNumarComanda() +
                " este in starea " + comanda.getState().getStateDescription());
    }

    @Override
    public String toString() {
        return id + "," + nume;
    }
}
