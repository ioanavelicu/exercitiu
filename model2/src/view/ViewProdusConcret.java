package src.view;

import src.model.Produs;

import java.util.List;

public class ViewProdusConcret implements IViewProdus{
    @Override
    public void showProduse(List<Produs> listaProduse) {
        listaProduse.forEach(System.out::println);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
