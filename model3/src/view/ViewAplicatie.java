package src.view;

import src.model.Client;
import src.model.Produs;
import src.model.Reclamatie;

import java.util.List;

public class ViewAplicatie {
    public void afiseazaMeniu() {
        System.out.println("Meniu aplicatie reclamatii:");
        System.out.println("1. Afiseaza reclamatii");
        System.out.println("2. Afiseaza produse");
        System.out.println("3. Afiseaza clienti");
        System.out.println("4. Subscribe client");
        System.out.println("5. Unsubscribe client");
        System.out.println("6. Adauga produs");
        System.out.println("7. Schimba stare reclamatie");
        System.out.println("8. Iesire");
    }

    public void afiseazaReclamatii(List<Reclamatie> listaReclamatii) {
        listaReclamatii.forEach(System.out::println);
    }

    public void afiseazaProduse(List<Produs> listaProduse) {
        listaProduse.forEach(System.out::println);
    }

    public void afiseazaClienti(List<Client> listaClienti) {
        listaClienti.forEach(System.out::println);
    }

    public void afiseazaTipuriDeReclamatie() {
        System.out.println("Starile disponiblie sunt:");
        System.out.println("1.INREGISTRARA");
        System.out.println("2.IN ANALIZA");
        System.out.println("3.SOLUTIONATA");
    }

    public void afiseazaMesaj(String mesaj) {
        System.out.println(mesaj);
    }
}
