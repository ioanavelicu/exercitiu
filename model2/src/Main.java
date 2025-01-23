package src;

import src.model.Client;
import src.model.Comanda;
import src.model.Produs;
import src.presenter.PresenterClient;
import src.presenter.PresenterCommanda;
import src.presenter.PresenterProdus;
import src.state.ComandaPlatitaState;
import src.view.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Client client = new Client();
//        Produs produs = new Produs();
//        Comanda comanda = new Comanda();
//
//        List<Client> listaClienti = client.preiaClientiDinFisier();
//        listaClienti.forEach(System.out::println);
//
//        System.out.println();
//
//        List<Produs> listaProduse = produs.preiaProduseDinFisier();
//        listaProduse.forEach(System.out::println);
//
//        System.out.println();
//
//        List<Comanda> listaComenzi = comanda.preiaComenziDinFisier();
//        listaComenzi.forEach(System.out::println);
//
//        listaComenzi.forEach(c -> {
//            c.setState(new ComandaPlatitaState());
//            c.notificaTotiClientii();
//        });

        IViewComanda viewComanda = new ViewComandaConcret();
        PresenterCommanda presenterCommanda = new PresenterCommanda(viewComanda);

        IViewClient viewClient = new ViewClientConcret();
        PresenterClient presenterClient = new PresenterClient(viewClient);

        IViewProdus viewProdus = new ViewProdusConcret();
        PresenterProdus presenterProdus = new PresenterProdus(viewProdus);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Meniu aplicatie comenzi:");
            System.out.println("1. Afisati comenzile");
            System.out.println("2. Afisati clienti");
            System.out.println("3. Afisati produse");
            System.out.println("4. Schimbati starea unei comenzi");
            System.out.println("5. Adauga un produs la comanda");
            System.out.println("6. Subscribe client");
            System.out.println("7. Unsubscribe client");
            System.out.println("8. Iesire");

            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1 -> presenterCommanda.afiseazaComenzi();
                case 2 -> presenterClient.afiseazaClienti();
                case 3 -> presenterProdus.afiseazaProduse();
                case 4 -> {
                    System.out.println("Introduceti numarul comenzii");
                    int nrComanda = scanner.nextInt();
                    System.out.println("Introduceti noua stare: PRELUATA, PREGATITA, PLATITA, ONORATA");
                    String stareNoua = scanner.next();
                    presenterCommanda.actualizareStareComanda(nrComanda, stareNoua);
                }
                case 5 -> {
                    System.out.println("Introduceti numarul comenzii");
                    int nrComanda = scanner.nextInt();
                    System.out.println("Introduceti idul produsului de adaugat");
                    int idProdus = scanner.nextInt();
                    presenterCommanda.actualizareListaProduse(nrComanda, idProdus);
                }
                case 6 -> {
                    System.out.println("Introduceti numarul comenzii:");
                    int numarComanda = scanner.nextInt();
                    System.out.println("Introduceti idul clientului:");
                    int client = scanner.nextInt();
                    presenterCommanda.subscribeClient(numarComanda, client);
                }
                case 7 -> {
                    System.out.println("Introduceti numarul comenzii:");
                    int numarComanda = scanner.nextInt();
                    System.out.println("Introduceti idul clientului:");
                    int client = scanner.nextInt();
                    presenterCommanda.unsubscribeClient(numarComanda, client);
                }
                case 8 -> {
                    return;
                }
            }
        }
    }
}
