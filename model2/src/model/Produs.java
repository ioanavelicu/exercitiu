package src.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Produs {
    private int id;
    private String denumire;

    private int pret;

    private static final String FILE_PATH = "produse.txt";

    public Produs() {
    }

    public Produs(int id, String denumire, int pret) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }

    public static List<Produs> preiaProduseDinFisier() {
        List<Produs> produse = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elementeProdus = line.split(",");
                int idProdus = Integer.parseInt(elementeProdus[0]);
                String denumire = elementeProdus[1];
                int pret = Integer.parseInt(elementeProdus[2]);
                produse.add(new Produs(idProdus, denumire, pret));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return produse;
    }

    public static void adaugaProdusInFisier(Produs produs) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(
                    produs.getId() + ","
                            + produs.getDenumire() + ","
                            + produs.getPret()
            );
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
