package src.state;

import src.model.Reclamatie;

public class ReclamatieInAnaliza implements IState{
    @Override
    public void actualizareStareReclamatie(Reclamatie reclamatie) {
        System.out.println("Reclamatia cu id ul " + reclamatie.getIdReclamatie() +
                " a fost analizata si va trece in starea solutionata");
        reclamatie.setStareReclamatie(new ReclamatieSolutionata());
    }

    @Override
    public String getDescriereStare() {
        return StareReclamatie.INANALIZA.getDescriereStare();
    }
}
