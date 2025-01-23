package src.model;

import src.observer.IObserver;
import src.state.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private int numarComanda;
    private List<Produs> listaProduse;
    private IState state;
    private List<IObserver> listaObservatori;

    private static final String FILE_PATH = "comenzi.txt";

    public Comanda() {
    }

    public Comanda(int numarComanda, List<Produs> listaProduse, IState state) {
        this.numarComanda = numarComanda;
        this.listaProduse = listaProduse;
        this.state = state;
        this.listaObservatori = new ArrayList<>();
    }

    public int getNumarComanda() {
        return numarComanda;
    }

    public void setNumarComanda(int numarComanda) {
        this.numarComanda = numarComanda;
    }

    public List<Produs> getListaProduse() {
        return listaProduse;
    }

    public void setListaProduse(List<Produs> listaProduse) {
        this.listaProduse = listaProduse;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public List<IObserver> getListaObservatori() {
        return listaObservatori;
    }

    public void setListaObservatori(List<IObserver> listaObservatori) {
        this.listaObservatori = listaObservatori;
    }

    public void subscribe(IObserver client) {
        this.listaObservatori.add(client);
    }

    public void unsubscribe(IObserver client) {
        listaObservatori.remove(client);
    }

    public void notificaTotiClientii() {
        for(IObserver client : listaObservatori) {
            client.primesteNotificare(this);
        }
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "numarComanda=" + numarComanda +
                ", listaProduse=" + listaProduse +
                ", state=" + state.getDenumireStare() +
                ", listaObservatori=" + listaObservatori +
                '}';
    }

    public static List<Comanda> preiaComenziDinFisier() {
        List<Comanda> comenzi = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elementeComanda = line.split(";");

                int nrComanda = Integer.parseInt(elementeComanda[0]);

                List<Produs> produse = new ArrayList<>();
                String[] elementeProduse = elementeComanda[1].split(",");
                for(int i = 0; i < elementeProduse.length; i+=3 ) {
                    produse.add(new Produs(Integer.parseInt(elementeProduse[i]), elementeProduse[i+1], Integer.parseInt(elementeProduse[i+2])));
                }

                String stareComanda = elementeComanda[2];
                IState state;
                switch (stareComanda.toUpperCase()) {
                    case "PREGATITA":
                        state = new ComandaPregatitaState();
                        break;
                    case "PLATITA":
                        state = new ComandaPlatitaState();
                        break;
                    case "ONORATA":
                        state = new ComandaOnorataState();
                        break;
                    default:
                        state = new ComandaPreluataState();
                        break;
                }

                Comanda comanda = new Comanda(nrComanda, produse, state);
                String listaObservatori = elementeComanda.length > 3 ? elementeComanda[3] : "";
                if(!listaObservatori.isEmpty()) {
                    String[] elementeClientiAbonati = listaObservatori.split(",");
                    for(int i = 0; i < elementeClientiAbonati.length; i+=2) {
                        Client client = new Client(Integer.parseInt(elementeClientiAbonati[i]), elementeClientiAbonati[i+1]);
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

    public static void salveazaComenziInFisier(List<Comanda> listaComenzi) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for(Comanda comanda : listaComenzi) {
                StringBuilder line = new StringBuilder();

                line.append(comanda.getNumarComanda()).append(";");

                for(Produs produs : comanda.getListaProduse()) {
                    line.append(produs.getId()).append(",")
                            .append(produs.getDenumire()).append(",")
                            .append(produs.getPret())
                            .append(",");
                }
                if (!comanda.listaProduse.isEmpty()) {
                    line.setLength(line.length() - 1);
                }

                line.append(";");
                line.append(comanda.getState().getDenumireStare());
                line.append(";");

                for(IObserver observer : comanda.getListaObservatori()) {
                    if(observer instanceof Client client) {
                        line.append(client.getId()).append(",").append(client.getNume()).append(",");
                    }
                }
                if (!comanda.listaObservatori.isEmpty()) {
                    line.setLength(line.length() - 1);
                }
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateComanda(Comanda comanda) {
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
