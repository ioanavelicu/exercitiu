package src.model;

import src.observer.IObserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client implements IObserver {
    private int id;
    private String nume;

    private static final String FILE_PATH = "clienti.txt";

    public Client() {
    }

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
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }

    @Override
    public void primesteNotificare(Comanda comanda) {
        System.out.println(this.nume + ": Comanda cu numarul " + comanda.getNumarComanda() +
                " are starea " + comanda.getState().getDenumireStare());
    }

    public static List<Client> preiaClientiDinFisier() {
        List<Client> clienti = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elementeClient = line.split(",");
                int idClient = Integer.parseInt(elementeClient[0]);
                String nume = elementeClient[1];
                clienti.add(new Client(idClient, nume));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clienti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id); // Comparăm doar după ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
