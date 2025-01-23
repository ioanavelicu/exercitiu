package src.controller;

import src.model.*;
import src.state.ReclamatieInAnaliza;
import src.state.ReclamatieInregistrata;
import src.state.ReclamatieSolutionata;
import src.view.ViewAplicatie;

import java.util.List;
import java.util.Scanner;

public class ControllerConcret {
    private final ClientMapper clientMapper;
    private final ProdusMapper produsMapper;
    private final ReclamatieMapper reclamatieMapper;
    private final List<Client> listaClienti;
    private final List<Produs> listaProduse;
    private final List<Reclamatie> listaReclamatii;
    private final ViewAplicatie viewAplicatie;

    public ControllerConcret(ViewAplicatie viewAplicatie) {
        this.viewAplicatie = viewAplicatie;
        this.clientMapper = new ClientMapper();
        this.produsMapper = new ProdusMapper();
        this.reclamatieMapper = new ReclamatieMapper();
        this.listaClienti = clientMapper.preiaClientiDinFisier();
        this.listaProduse = produsMapper.preiaProduseDinFisier();
        this.listaReclamatii = reclamatieMapper.preiaReclamatiiDinFisier();
    }

    public void afiseazaReclamatii() {
        viewAplicatie.afiseazaReclamatii(listaReclamatii);
    }

    public void afiseazaProduse() {
        viewAplicatie.afiseazaProduse(listaProduse);
    }

    public void afiseazaClienti() {
        viewAplicatie.afiseazaClienti(listaClienti);
    }

    public void actualizareStareReclamatie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti id ul reclamatiei");
        int idReclamatie = scanner.nextInt();

        for (Reclamatie reclamatie : listaReclamatii) {
            if (reclamatie.getIdReclamatie() == idReclamatie) {
                viewAplicatie.afiseazaTipuriDeReclamatie();
                System.out.println("Alegeti noua stare");
                int stare = scanner.nextInt();
                switch (stare) {
                    case 1-> reclamatie.setStareReclamatie(new ReclamatieInregistrata());
                    case 2-> reclamatie.setStareReclamatie(new ReclamatieInAnaliza());
                    case 3 -> reclamatie.setStareReclamatie(new ReclamatieSolutionata());
                    default -> {
                        return;
                    }
                }
                reclamatieMapper.updateReclamatie(reclamatie);
                viewAplicatie.afiseazaMesaj("stare updatata cu succes");
                break;
            }
        }
    }

    public void subscribeClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti id ul reclamatiei");
        int idReclamatie = scanner.nextInt();

        for (Reclamatie reclamatie : listaReclamatii) {
            if (reclamatie.getIdReclamatie() == idReclamatie) {
                System.out.println("introduceti idul clientului");
                int idClient = scanner.nextInt();
                Client client = listaClienti.stream()
                        .filter(c -> c.getIdClient() == idClient)
                        .findFirst()
                        .get();
                reclamatie.subscribe(client);
                reclamatieMapper.salveazaReclamatiiInFisier(listaReclamatii);
                viewAplicatie.afiseazaMesaj("clientul " + client.getNume() + " este subscribed");
                break;
            }
        }
    }

    public void unsubscribeClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti id ul reclamatiei");
        int idReclamatie = scanner.nextInt();

        for (Reclamatie reclamatie : listaReclamatii) {
            if (reclamatie.getIdReclamatie() == idReclamatie) {
                System.out.println("introduceti idul clientului");
                int idClient = scanner.nextInt();
                Client client = listaClienti.stream()
                        .filter(c -> c.getIdClient() == idClient)
                        .findFirst()
                        .get();
                reclamatie.unsubscribe(client);
                reclamatieMapper.salveazaReclamatiiInFisier(listaReclamatii);
                viewAplicatie.afiseazaMesaj("clientul " + client.getNume() + " este unsubscribed");
                break;
            }
        }
    }

    public void adaugaProdusInReclamatie() {
        return;
    }
}
