package src.view;

import src.model.Client;

import java.util.List;

public class ViewClientConcret implements IViewClient{
    @Override
    public void showClienti(List<Client> listaClienti) {
        listaClienti.forEach(System.out::println);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
