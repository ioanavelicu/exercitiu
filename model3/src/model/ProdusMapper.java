package src.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdusMapper {
    private static final String FILE_PATH = "produse.txt";

    public List<Produs> preiaProduseDinFisier() {
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
}
