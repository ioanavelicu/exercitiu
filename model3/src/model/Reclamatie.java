package src.model;

import src.observer.IObserver;
import src.state.IState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reclamatie {
    private int idReclamatie;
    private List<Produs> listaProduse;

    private IState stareReclamatie;

    private List<IObserver> listaObservatori = new ArrayList<>();

    public Reclamatie(int idReclamatie, List<Produs> listaProduse, IState stareReclamatie) {
        this.idReclamatie = idReclamatie;
        this.listaProduse = listaProduse;
        this.stareReclamatie = stareReclamatie;
    }

    public int getIdReclamatie() {
        return idReclamatie;
    }

    public void setIdReclamatie(int idReclamatie) {
        this.idReclamatie = idReclamatie;
    }

    public List<Produs> getListaProduse() {
        return listaProduse;
    }

    public void setListaProduse(List<Produs> listaProduse) {
        this.listaProduse = listaProduse;
    }

    public IState getStareReclamatie() {
        return stareReclamatie;
    }

    public void setStareReclamatie(IState stareReclamatie) {
        this.stareReclamatie = stareReclamatie;
        notificaTotiObservatorii();
    }

    public List<IObserver> getListaObservatori() {
        return listaObservatori;
    }

    public void setListaObservatori(List<IObserver> listaObservatori) {
        this.listaObservatori = listaObservatori;
    }

    @Override
    public String toString() {
        return "Reclamatie{" +
                "idReclamatie=" + idReclamatie +
                ", listaProduse=" + listaProduse +
                ", stareReclamatie=" + stareReclamatie.getDescriereStare() +
                ", listaObservatori=" + listaObservatori +
                '}';
    }

    public void subscribe(Client client) {
        this.listaObservatori.add(client);
    }

    public void unsubscribe(Client client) {
        this.listaObservatori.remove(client);
    }

    public void notificaTotiObservatorii() {
        for(IObserver observator : listaObservatori) {
            observator.primesteNotificare(this);
        }
    }
}
