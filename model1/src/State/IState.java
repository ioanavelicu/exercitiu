package src.State;

import src.Model.Comanda;

public interface IState {
    void updateState(Comanda comanda);

    String getStateDescription();
}
