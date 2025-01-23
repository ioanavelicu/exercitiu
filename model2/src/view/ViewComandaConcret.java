package src.view;

import src.model.Comanda;

import java.util.List;

public class ViewComandaConcret implements IViewComanda{
    @Override
    public void showComenzi(List<Comanda> listaComenzi) {
        listaComenzi.forEach(System.out::println);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
