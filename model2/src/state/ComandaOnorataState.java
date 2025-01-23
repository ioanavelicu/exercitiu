package src.state;

import src.model.Comanda;

public class ComandaOnorataState implements IState {
    @Override
    public void actualizeazaStare(Comanda comanda) {
        System.out.println("Comanda cu numarul " + comanda.getNumarComanda() +
                " a fost onorata.");
    }

    @Override
    public String getDenumireStare() {
        return State.ONORATA.getDenumire();
    }
}
