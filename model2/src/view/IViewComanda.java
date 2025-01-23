package src.view;

import src.model.Comanda;

import java.util.List;

public interface IViewComanda {
    void showComenzi(List<Comanda>listaComenzi);
    void showMessage(String message);
}
