package src.State;

import src.Model.Comanda;

public class ComandaPlasataState implements IState{
    @Override
    public void updateState(Comanda comanda) {
        System.out.println("Comanda cu numarul " + comanda.getNumarComanda() +
                " a fost plasata.");
    }

    @Override
    public String getStateDescription() {
        return State.PLASATA.getDescription();
    }
}
