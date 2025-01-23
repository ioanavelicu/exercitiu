package src.state;

import src.model.Comanda;

public class ComandaPreluataState implements IState {
    @Override
    public void actualizeazaStare(Comanda comanda) {
        System.out.println("Comanda cu numarul " + comanda.getNumarComanda() + " este preluata si urmeaza" +
                " sa fie pregatita");
        comanda.setState(new ComandaPregatitaState());
    }

    @Override
    public String getDenumireStare() {
        return State.PRELUATA.getDenumire();
    }
}
