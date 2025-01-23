package src.State;

import src.Model.Comanda;

public class ComandaPregatitaState implements IState{
    @Override
    public void updateState(Comanda comanda) {
        System.out.println("Comanda cu numarul " + comanda.getNumarComanda()
                + " a fost pregatita si trebuie sa fie platita");
        comanda.setState(new ComandaPlatitaState());
    }

    @Override
    public String getStateDescription() {
        return State.PREGATITA.getDescription();
    }
}
