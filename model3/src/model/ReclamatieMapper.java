package src.model;

import src.observer.IObserver;
import src.state.IState;
import src.state.ReclamatieInAnaliza;
import src.state.ReclamatieInregistrata;
import src.state.ReclamatieSolutionata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamatieMapper {
    private static final String FILE_PATH = "reclamatii.txt";

    public List<Reclamatie> preiaReclamatiiDinFisier() {
        List<Reclamatie> reclamatii = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elementeReclamatie = line.split(";");

                int idReclamatie = Integer.parseInt(elementeReclamatie[0]);

                List<Produs> produse = new ArrayList<>();
                String[] elementeProduse = elementeReclamatie[1].split(",");
                for(int i = 0; i < elementeProduse.length; i+=3 ) {
                    produse.add(new Produs(Integer.parseInt(elementeProduse[i]), elementeProduse[i+1], Integer.parseInt(elementeProduse[i+2])));
                }

                String stareReclamatie = elementeReclamatie[2];
                IState state;
                switch (stareReclamatie.toUpperCase()) {
                    case "IN ANALIZA":
                        state = new ReclamatieInAnaliza();
                        break;
                    case "SOLUTIONATA":
                        state = new ReclamatieSolutionata();
                        break;
                    default:
                        state = new ReclamatieInregistrata();
                        break;
                }

                Reclamatie reclamatie = new Reclamatie(idReclamatie, produse, state);

                String listaObservatori = elementeReclamatie.length > 3 ? elementeReclamatie[3] : "";
                if(!listaObservatori.isEmpty()) {
                    String[] elementeClientiAbonati = listaObservatori.split(",");
                    for(int i = 0; i < elementeClientiAbonati.length; i+=2) {
                        Client client = new Client(Integer.parseInt(elementeClientiAbonati[i]), elementeClientiAbonati[i+1]);
                        reclamatie.subscribe(client);
                    }
                }

                reclamatii.add(reclamatie);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reclamatii;
    }

    public void salveazaReclamatiiInFisier(List<Reclamatie> listaReclamatii) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for(Reclamatie reclamatie : listaReclamatii) {
                StringBuilder line = new StringBuilder();

                line.append(reclamatie.getIdReclamatie()).append(";");

                for(Produs produs : reclamatie.getListaProduse()) {
                    line.append(produs.getIdProdus()).append(",")
                            .append(produs.getDenumire()).append(",")
                            .append(produs.getPret())
                            .append(",");
                }
                if (!reclamatie.getListaProduse().isEmpty()) {
                    line.setLength(line.length() - 1);
                }

                line.append(";");
                line.append(reclamatie.getStareReclamatie().getDescriereStare());
                line.append(";");

                for(IObserver observer : reclamatie.getListaObservatori()) {
                    if(observer instanceof Client client) {
                        line.append(client.getIdClient()).append(",").append(client.getNume()).append(",");
                    }
                }
                if (!reclamatie.getListaObservatori().isEmpty()) {
                    line.setLength(line.length() - 1);
                }
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateReclamatie(Reclamatie reclamatie) {
        List<Reclamatie> reclamatii = preiaReclamatiiDinFisier();

        for(Reclamatie r : reclamatii) {
            if (r.getIdReclamatie() == reclamatie.getIdReclamatie()) {
                r.setStareReclamatie(reclamatie.getStareReclamatie());
                break;
            }
        }

        salveazaReclamatiiInFisier(reclamatii);
    }

    public void adaugaProdus(Reclamatie reclamatie, Produs produs) {
        List<Reclamatie> reclamatii = preiaReclamatiiDinFisier();

        for(Reclamatie r : reclamatii) {
            if (r.getIdReclamatie() == reclamatie.getIdReclamatie()) {
                r.getListaProduse().add(produs);
                break;
            }
        }

        salveazaReclamatiiInFisier(reclamatii);
    }
}
