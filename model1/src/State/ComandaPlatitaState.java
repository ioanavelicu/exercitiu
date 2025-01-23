package src.State;

import src.Model.Comanda;

public class ComandaPlatitaState implements IState{
    @Override
    public void updateState(Comanda comanda) {
        System.out.println("Comanda cu numarul " + comanda.getNumarComanda() +
                " a fost platita si urmeaza sa fie plasata");
        comanda.setState(new ComandaPlasataState());
    }

    @Override
    public String getStateDescription() {
        return State.PLATITA.getDescription();
    }
}
