package src.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientMapper {
    private static final String FILE_PATH = "clienti.txt";

    public List<Client> preiaClientiDinFisier() {
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
}
