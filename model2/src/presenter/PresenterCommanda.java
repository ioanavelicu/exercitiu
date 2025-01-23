package src.presenter;

import src.model.Client;
import src.model.Comanda;
import src.model.Produs;
import src.state.*;
import src.view.IViewComanda;

import java.util.List;

public class PresenterCommanda {
    private IViewComanda viewComanda;
    //daca era mvc puneam aici si clasele din model pentru actualizari

    public PresenterCommanda(IViewComanda viewComanda) {
        this.viewComanda = viewComanda;
    }

    public void afiseazaComenzi() {
        List<Comanda> listaComenzi = Comanda.preiaComenziDinFisier();
        viewComanda.showComenzi(listaComenzi);
    }

    public void actualizareStareComanda(int numarComanda, String stare) {
        List<Comanda> listaComenzi = Comanda.preiaComenziDinFisier();
        for(Comanda comanda : listaComenzi) {
            if(comanda.getNumarComanda() == numarComanda) {
                switch (stare.toUpperCase()) {
                    case "PRELUATA" -> comanda.setState(new ComandaPreluataState());
                    case "PREGATITA" -> comanda.setState(new ComandaPregatitaState());
                    case "PLATITA" -> comanda.setState(new ComandaPlatitaState());
                    case "ONORATA" -> comanda.setState(new ComandaOnorataState());
                }
                comanda.notificaTotiClientii();
                Comanda.updateComanda(comanda);
                viewComanda.showMessage("Starea comenzii a fost actualizata");
                break;
            }
        }
    }

    public void actualizareListaProduse(int numarComanda, int idProdus) {
        List<Comanda> listaComenzi = Comanda.preiaComenziDinFisier();

        for(Comanda comanda : listaComenzi) {
            if(comanda.getNumarComanda() == numarComanda) {
                List<Produs> listaProduse = Produs.preiaProduseDinFisier();
                for(Produs produs : listaProduse) {
                    if(produs.getId() == idProdus) {
                        Comanda.adaugaProdus(comanda, produs);
                        viewComanda.showMessage("produsul a fost adaugat");
                        break;
                    }
                }
            }
        }
    }

    public void subscribeClient(int numarComanda, int idClient) {
        List<Comanda> listaComenzi = Comanda.preiaComenziDinFisier();
        List<Client> listaClienti = Client.preiaClientiDinFisier();
        for(Comanda comanda : listaComenzi) {
            if(comanda.getNumarComanda() == numarComanda) {
                Client client = listaClienti.stream()
                                .filter(c -> c.getId() == idClient)
                                        .findFirst()
                                                .get();
                comanda.subscribe(client);
                Comanda.salveazaComenziInFisier(listaComenzi);
                viewComanda.showMessage("clientul " + client.getNume() + "a fost abonat cu succes");
                break;
            }
        }
    }

    public void unsubscribeClient(int numarComanda, int idClient) {
        List<Comanda> listaComenzi = Comanda.preiaComenziDinFisier();
        List<Client> listaClienti = Client.preiaClientiDinFisier();
        for(Comanda comanda : listaComenzi) {
            if(comanda.getNumarComanda() == numarComanda) {
                Client client = listaClienti.stream()
                        .filter(c -> c.getId() == idClient)
                        .findFirst()
                        .get();
                comanda.unsubscribe(client);
                Comanda.salveazaComenziInFisier(listaComenzi);
                viewComanda.showMessage("clientul " + client.getNume() + " a fost dezabonat cu succes");
                break;
            }
        }
    }
}
