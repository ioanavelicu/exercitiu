package src.state;

import src.model.Comanda;

public class ComandaPregatitaState implements IState {
    @Override
    public void actualizeazaStare(Comanda comanda) {
        System.out.println("Comanda cu numarul " + comanda.getNumarComanda()
                + " a fost pregatita si trebuie sa fie platita");
        comanda.setState(new ComandaPlatitaState());
    }

    @Override
    public String getDenumireStare() {
        return State.PREGATITA.getDenumire();
    }
}
