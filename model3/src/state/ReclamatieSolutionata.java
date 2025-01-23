package src.state;

import src.model.Reclamatie;

public class ReclamatieSolutionata implements IState{
    @Override
    public void actualizareStareReclamatie(Reclamatie reclamatie) {
        System.out.println("Reclamatia cu id ul " + reclamatie.getIdReclamatie() +
                " a fost solutionata");
    }

    @Override
    public String getDescriereStare() {
        return StareReclamatie.SOLUTIONATA.getDescriereStare();
    }
}
