package src.presenter;

import src.model.Produs;
import src.view.IViewProdus;

import java.util.List;

public class PresenterProdus {
    private IViewProdus viewProdus;

    public PresenterProdus(IViewProdus viewProdus) {
        this.viewProdus = viewProdus;
    }

    public void afiseazaProduse() {
        List<Produs> listaProduse = Produs.preiaProduseDinFisier();
        viewProdus.showProduse(listaProduse);
    }
}
