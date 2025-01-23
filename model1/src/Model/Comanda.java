package src.Model;

import src.Observer.IObserver;
import src.State.ComandaPreluataState;
import src.State.IState;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private int numarComanda;
    private String adresaLivrare;
    private double valoare;
    private IState state;
    private List<IObserver> listaObservatoriClienti = new ArrayList<>();

    public Comanda(int numarComanda, String adresaLivrare, double valoare) {
        this.numarComanda = numarComanda;
        this.adresaLivrare = adresaLivrare;
        this.valoare = valoare;
        this.listaObservatoriClienti = new ArrayList<>();
        this.state = new ComandaPreluataState();
    }

    public Comanda(int numarComanda, String adresaLivrare, double valoare, IState state) {
        this.numarComanda = numarComanda;
        this.adresaLivrare = adresaLivrare;
        this.valoare = valoare;
        this.state = state;
    }

    public int getNumarComanda() {
        return numarComanda;
    }

    public void setNumarComanda(int numarComanda) {
        this.numarComanda = numarComanda;
    }

    public String getAdresaLivrare() {
        return adresaLivrare;
    }

    public void setAdresaLivrare(String adresaLivrare) {
        this.adresaLivrare = adresaLivrare;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public List<IObserver> getListaObservatoriClienti() {
        return listaObservatoriClienti;
    }

    public void subscribe(IObserver observer) {
        this.listaObservatoriClienti.add(observer);
    }

    public void unsubscribe(IObserver observer) {
        this.listaObservatoriClienti.remove(observer);
    }

    public void notificaTotiClientii() {
        for(IObserver observer : listaObservatoriClienti) {
            observer.primesteUpdateDeLaComanda(this);
        }
    }

    public void updateState() {
        this.state.updateState(this);
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "numarComanda=" + numarComanda +
                ", adresaLivrare='" + adresaLivrare + '\'' +
                ", valoare=" + valoare +
                ", state=" + state.getStateDescription() +
                '}';
    }
}
