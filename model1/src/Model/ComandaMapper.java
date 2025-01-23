package src.Model;

import src.Observer.IObserver;
import src.State.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComandaMapper {
    private static final String FILE_PATH = "comenzi.txt";

    public List<Comanda> preiaComenziDinFisier() {
        List<Comanda> comenzi = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elementeComanda = line.split(",");
                int numarComanda = Integer.parseInt(elementeComanda[0]);
                String adresa = elementeComanda[1];
                double valoare = Double.parseDouble(elementeComanda[2]);
                String stareComanda = elementeComanda[3];

                IState state;
                switch (stareComanda) {
                    case "Pregatita":
                        state = new ComandaPregatitaState();
                        break;
                    case "Platita":
                        state = new ComandaPlatitaState();
                        break;
                    case "Plasata":
                        state = new ComandaPlasataState();
                        break;
                    default:
                        state = new ComandaPreluataState();
                        break;
                }

                Comanda comanda = new Comanda(numarComanda, adresa, valoare, state);
                String listaObservatori = elementeComanda.length > 4 ? elementeComanda[4] : "";
                if(!listaObservatori.isEmpty()) {
                    String[] clienti = listaObservatori.split(",");
                    int i = 1;
                    for(String numeClient : clienti) {
                        Client client = new Client(i++, numeClient);
                        comanda.subscribe(client);
                    }
                }

                comenzi.add(comanda);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return comenzi;
    }

    public void salveazaComenziInFisier(List<Comanda> listaComenzi) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for(Comanda comanda : listaComenzi) {
                StringBuilder observatori = new StringBuilder();
                for(IObserver client : comanda.getListaObservatoriClienti()) {
                    if(client instanceof Client) {
                        observatori.append(((Client) client).getNume()).append(",");
                    }
                }
                writer.write(
                        comanda.getNumarComanda() + ","
                        + comanda.getAdresaLivrare() + ","
                        + comanda.getValoare() + ","
                        + comanda.getState().getStateDescription() + ","
                        + (observatori.length() > 0 ? observatori : "")
                );
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateComanda(Comanda comanda) {
        List<Comanda> comenzi = preiaComenziDinFisier();

        for(Comanda c : comenzi) {
            if (c.getNumarComanda() == comanda.getNumarComanda()) {
                c.setState(comanda.getState());
                break;
            }
        }

        salveazaComenziInFisier(comenzi);
    }
}
