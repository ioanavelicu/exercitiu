package src.view;

import src.model.Client;

import java.util.List;

public interface IViewClient {
    void showClienti(List<Client> listaClienti);
    void showMessage(String message);
}
