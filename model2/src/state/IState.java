package src.state;

import src.model.Comanda;

public interface IState {
    void actualizeazaStare(Comanda comanda);
    String getDenumireStare();
}
