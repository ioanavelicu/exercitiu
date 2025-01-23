package src.state;

import src.model.Reclamatie;

public class ReclamatieInregistrata implements IState{
    @Override
    public void actualizareStareReclamatie(Reclamatie reclamatie) {
        System.out.println("Reclamatia cu id ul " + reclamatie.getIdReclamatie() +
                " a fost inregistrata si va trece in analiza");
        reclamatie.setStareReclamatie(new ReclamatieInAnaliza());
    }

    @Override
    public String getDescriereStare() {
        return StareReclamatie.INREGISTRATA.getDescriereStare();
    }
}
