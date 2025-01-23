package src;

import src.Controller.ComandaController;
import src.Model.Client;
import src.Model.ClientMapper;
import src.Model.Comanda;
import src.Model.ComandaMapper;
import src.State.ComandaPlatitaState;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Client client1 = new Client("Ioana");
//        Client client2 = new Client("Andreea");
//        Client client3 = new Client("Cristi");
//
//        Comanda comanda1 = new Comanda(1, "adr1", 1200);
//        Comanda comanda2 = new Comanda(2, "adr2", 100);
//
//        comanda1.subscribe(client1);
//        comanda2.subscribe(client2);
//        comanda1.subscribe(client3);
//
//        comanda1.setState(new ComandaPlatitaState());
//        comanda1.updateState();
//        comanda1.notificaTotiClientii();
//        comanda2.notificaTotiClientii();

        ClientMapper clientMapper = new ClientMapper();
        ComandaMapper comandaMapper = new ComandaMapper();

//        List<Client> listClienti = clientMapper.preiaClientiDinFisier();
//        listClienti.forEach(System.out::println);
//
//        List<Comanda> listaComenzi = comandaMapper.preiaComenziDinFisier();
//        listaComenzi.forEach(System.out::println);

        ComandaController comandaController = new ComandaController(comandaMapper, clientMapper);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Meniu aplicatie comenzi: ");
            System.out.println("   1. Afisati comenzile");
            System.out.println("   2. Afisati clientii");
            System.out.println("   3. Schimba stare comanda");
            System.out.println("   4. Subscribe client");
            System.out.println("   5. Unsubscribe client");
            System.out.println("   6. Iesire");

            int option = scanner.nextInt();
            switch (option) {
                case 1 -> comandaController.displayComenzi();
                case 2 -> comandaController.displayClienti();
                case 3 -> {
                    comandaController.displayComenzi();
                    System.out.println("Introduceti numarul comenzii:");
                    Scanner nr = new Scanner(System.in);
                    int numarComanda = nr.nextInt();
                    comandaController.modificaStateComanda(numarComanda);
                }
                case 4 -> {
                    comandaController.displayComenzi();
                    System.out.println("Introduceti numarul comenzii:");
                    Scanner nr = new Scanner(System.in);
                    int numarComanda = nr.nextInt();
                    comandaController.subscribeClientLaComanda(numarComanda);
                }
                case 5 -> {
                    comandaController.displayComenzi();
                    System.out.println("Introduceti numarul comenzii:");
                    Scanner nr = new Scanner(System.in);
                    int numarComanda = nr.nextInt();
                    comandaController.unsubscribeClientLaComanda(numarComanda);
                }
                case 6 -> {
                    comandaController.salvare();
                    return;
                }
            }
        }
    }
}
