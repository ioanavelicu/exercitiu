package src.Controller;

import src.Model.Client;
import src.Model.ClientMapper;
import src.Model.Comanda;
import src.Model.ComandaMapper;
import src.State.ComandaPlasataState;
import src.State.ComandaPlatitaState;
import src.State.ComandaPregatitaState;
import src.State.ComandaPreluataState;

import java.util.List;
import java.util.Scanner;

public class ComandaController {
    private ComandaMapper comandaMapper;
    private ClientMapper clientMapper;
    private List<Comanda> listaComenzi;
    private List<Client> listaClienti;

    public ComandaController(ComandaMapper comandaMapper, ClientMapper clientMapper) {
        this.comandaMapper = comandaMapper;
        this.clientMapper = clientMapper;
        this.listaComenzi = comandaMapper.preiaComenziDinFisier();
        this.listaClienti = clientMapper.preiaClientiDinFisier();
    }

    public void displayComenzi() {
        System.out.println("Lista comenzilor este: ");
        this.listaComenzi.forEach(System.out::println);
    }

    public void displayClienti() {
        System.out.println("Lista de clientie este: ");
        this.listaClienti.forEach(System.out::println);
    }

    public void modificaStateComanda(int numarComanda) {
        Comanda comanda = this.listaComenzi
                .stream()
                .filter(c -> c.getNumarComanda() == numarComanda)
                .findFirst()
                .get();

        if(comanda != null) {
            System.out.println("Starile disponibile sunt:");
            System.out.println("1. PRELUATA");
            System.out.println("2. PREGATITA");
            System.out.println("3. PLATITA");
            System.out.println("4. PLASATA");
            System.out.println("Starea actuala este: " + comanda.getState().getStateDescription());

            System.out.println("Alegeti o stare");
            Scanner scanner = new Scanner(System.in);
            int stareNoua = scanner.nextInt();

            switch (stareNoua) {
                case 1 -> comanda.setState(new ComandaPreluataState());
                case 2 -> comanda.setState(new ComandaPregatitaState());
                case 3 -> comanda.setState(new ComandaPlatitaState());
                case 4 -> comanda.setState(new ComandaPlasataState());
                default -> System.out.println("optiune invalida");
            }

            comandaMapper.updateComanda(comanda);
            comanda.notificaTotiClientii();
            System.out.println("Comanda a fost updatata cu succes");
        } else {
            System.out.println("Comanda nu exista!");
        }
    }

    public void subscribeClientLaComanda(int numarComanda) {
        Comanda comanda = this.listaComenzi
                .stream()
                .filter(c -> c.getNumarComanda() == numarComanda)
                .findFirst()
                .get();

        System.out.println("Clientii abonati sunt: ");
        comanda.getListaObservatoriClienti().forEach(System.out::println);

        System.out.println("Alegeti clientul pe care il abonati");
        Scanner scanner = new Scanner(System.in);
        int idAbonat = scanner.nextInt();
        Client client = this.listaClienti
                .stream()
                .filter(c -> c.getId() == idAbonat)
                .findFirst()
                .get();
        comanda.subscribe(client);
        this.comandaMapper.salveazaComenziInFisier(this.listaComenzi);
        System.out.println("Clientul " + client.getNume() + " a fost abonat cu succes");
    }

    public void unsubscribeClientLaComanda(int numarComanda) {
        Comanda comanda = this.listaComenzi
                .stream()
                .filter(c -> c.getNumarComanda() == numarComanda)
                .findFirst()
                .get();

        System.out.println("Clientii abonati sunt: ");
        comanda.getListaObservatoriClienti().forEach(System.out::println);

        System.out.println("Alegeti clientul pe care il dezabonati");
        Scanner scanner = new Scanner(System.in);
        int idAbonat = scanner.nextInt();
        Client client = this.listaClienti
                .stream()
                .filter(c -> c.getId() == idAbonat)
                .findFirst()
                .get();
        comanda.unsubscribe(client);
        this.comandaMapper.salveazaComenziInFisier(this.listaComenzi);
        System.out.println("Clientul " + client.getNume() + " a fost dezabonat cu succes");
    }

    public void salvare() {
        this.comandaMapper.salveazaComenziInFisier(this.listaComenzi);
    }
}
