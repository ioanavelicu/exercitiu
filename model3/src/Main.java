package src;

import src.controller.ControllerConcret;
import src.model.*;
import src.state.ReclamatieInAnaliza;
import src.state.ReclamatieInregistrata;
import src.state.ReclamatieSolutionata;
import src.view.ViewAplicatie;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientMapper clientMapper = new ClientMapper();
        ProdusMapper produsMapper = new ProdusMapper();
        ReclamatieMapper reclamatieMapper = new ReclamatieMapper();

        List<Client> listaClienti = clientMapper.preiaClientiDinFisier();
        List<Produs> listaProduse = produsMapper.preiaProduseDinFisier();
        List<Reclamatie> listaRclamatii = reclamatieMapper.preiaReclamatiiDinFisier();

        listaClienti.forEach(System.out::println);
        System.out.println();
        listaProduse.forEach(System.out::println);
        System.out.println();
        listaRclamatii.forEach(System.out::println);

        System.out.println();
        Client client1 = new Client(100, "Maria");
        Client client2 = new Client(101, "Cristina");
        Reclamatie reclamatie = new Reclamatie(100, null, new ReclamatieInregistrata());
        reclamatie.subscribe(client1);
        reclamatie.subscribe(client2);
        reclamatie.setStareReclamatie(new ReclamatieSolutionata());
        reclamatie.unsubscribe(client1);
        reclamatie.setStareReclamatie(new ReclamatieInAnaliza());

        ViewAplicatie viewAplicatie = new ViewAplicatie();
        ControllerConcret controllerConcret = new ControllerConcret(viewAplicatie);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            viewAplicatie.afiseazaMeniu();
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1 -> controllerConcret.afiseazaReclamatii();
                case 2 -> controllerConcret.afiseazaProduse();
                case 3 -> controllerConcret.afiseazaClienti();
                case 4 -> controllerConcret.subscribeClient();
                case 5 -> controllerConcret.unsubscribeClient();
                case 6 -> controllerConcret.adaugaProdusInReclamatie();
                case 7 -> controllerConcret.actualizareStareReclamatie();
                case 8 -> {
                    return;
                }
            }
        }
    }
}
