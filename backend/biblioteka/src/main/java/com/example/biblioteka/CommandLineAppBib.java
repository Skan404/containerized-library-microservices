package com.example.biblioteka;

import com.example.biblioteka.entity.Biblioteka;
import com.example.biblioteka.service.BibliotekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineAppBib implements CommandLineRunner {

    private final BibliotekaService bibliotekaService;

    @Autowired
    public CommandLineAppBib(BibliotekaService bibliotekaService) {
        this.bibliotekaService = bibliotekaService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Witaj w aplikacji Biblioteka ===");

        while (running) {
            System.out.println("\nDostępne komendy:");
            System.out.println("1. Wyświetl wszystkie biblioteki");
            System.out.println("2. Dodaj nową bibliotekę");
            System.out.println("3. Usuń bibliotekę");
            System.out.println("4. Wyjdź");
            System.out.print("Wybierz komendę: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayBiblioteki();
                    break;
                case "2":
                    addBiblioteka(scanner);
                    break;
                case "3":
                    deleteBiblioteka(scanner);
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Nieznana komenda.");
            }
        }

        System.out.println("=== Do zobaczenia! ===");
    }

    private void displayBiblioteki() {
        List<Biblioteka> biblioteki = bibliotekaService.findAll();
        System.out.println("\nLista bibliotek:");
        biblioteki.forEach(System.out::println);
    }

    private void addBiblioteka(Scanner scanner) {
        System.out.print("Podaj nazwę biblioteki: ");
        String nazwa = scanner.nextLine();

        System.out.print("Podaj lokalizację biblioteki: ");
        String lokalizacja = scanner.nextLine();

        Biblioteka biblioteka = new Biblioteka();
        biblioteka.setId(UUID.randomUUID());
        biblioteka.setNazwa(nazwa);
        biblioteka.setLokalizacja(lokalizacja);

        bibliotekaService.save(biblioteka);
        System.out.println("Biblioteka została dodana.");
    }

    private void deleteBiblioteka(Scanner scanner) {
        displayBiblioteki();
        System.out.print("Podaj ID biblioteki do usunięcia: ");
        String id = scanner.nextLine();

        try {
            bibliotekaService.deleteById(UUID.fromString(id));
            System.out.println("Biblioteka została usunięta.");
        } catch (IllegalArgumentException e) {
            System.err.println("Nie znaleziono biblioteki o podanym ID.");
        }
    }
}
