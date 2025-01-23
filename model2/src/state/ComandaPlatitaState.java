package src.state;

import src.model.Comanda;

public class ComandaPlatitaState implements IState {
    @Override
    public void actualizeazaStare(Comanda comanda) {
        System.out.println("Comanda cu numarul " + comanda.getNumarComanda() +
                " a fost platita si urmeaza sa fie plasata");
        comanda.setState(new ComandaOnorataState());
    }

    @Override
    public String getDenumireStare() {
        return State.PLATITA.getDenumire();
    }
}
