package src.model;

import src.observer.IObserver;

import java.util.Objects;

public class Client implements IObserver {
    private int idClient;
    private String nume;

    public Client(int idClient, String nume) {
        this.idClient = idClient;
        this.nume = nume;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nume='" + nume + '\'' +
                '}';
    }

    @Override
    public void primesteNotificare(Reclamatie reclamatie) {
        System.out.println(nume + " Reclamatia cu numarul " + reclamatie.getIdReclamatie() +
                " are starea " + reclamatie.getStareReclamatie().getDescriereStare());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(idClient, client.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idClient);
    }
}
