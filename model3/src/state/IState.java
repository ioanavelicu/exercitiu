package src.state;

import src.model.Reclamatie;

public interface IState {
    void actualizareStareReclamatie(Reclamatie reclamatie);
    String getDescriereStare();
}
