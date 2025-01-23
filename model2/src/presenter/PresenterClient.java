package src.presenter;

import src.model.Client;
import src.view.IViewClient;

import java.util.List;

public class PresenterClient {
    private IViewClient viewClient;

    public PresenterClient(IViewClient viewClient) {
        this.viewClient = viewClient;
    }

    public void afiseazaClienti() {
        List<Client> listaClienti = Client.preiaClientiDinFisier();
        this.viewClient.showClienti(listaClienti);
    }
}
