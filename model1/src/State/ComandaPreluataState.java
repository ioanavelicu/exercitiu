package src.State;

import src.Model.Comanda;

public class ComandaPreluataState implements IState{

    @Override
    public void updateState(Comanda comanda) {
        System.out.println("Comanda cu numarul " + comanda.getNumarComanda() + " este preluata si urmeaza" +
                " sa fie pregatita");
        comanda.setState(new ComandaPregatitaState());
    }

    @Override
    public String getStateDescription() {
        return State.PRELUATA.getDescription();
    }
}
