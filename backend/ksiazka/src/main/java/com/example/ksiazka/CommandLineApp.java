package com.example.ksiazka;

import com.example.ksiazka.entity.Ksiazka;
import com.example.ksiazka.service.KsiazkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineApp implements CommandLineRunner {

    private final KsiazkaService ksiazkaService;

    @Autowired
    public CommandLineApp(KsiazkaService ksiazkaService) {
        this.ksiazkaService = ksiazkaService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Witaj w aplikacji KsiazkaService ===");

        while (running) {
            System.out.println("\nDostępne komendy:");
            System.out.println("1. Wyświetl wszystkie książki");
            System.out.println("2. Dodaj nową książkę");
            System.out.println("3. Usuń książkę");
            System.out.println("4. Wyjdź");
            System.out.print("Wybierz komendę: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayKsiazki();
                    break;
                case "2":
                    addKsiazka(scanner);
                    break;
                case "3":
                    deleteKsiazka(scanner);
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

    private void displayKsiazki() {
        List<Ksiazka> ksiazki = ksiazkaService.findAll();
        System.out.println("\nLista książek:");
        ksiazki.forEach(System.out::println);
    }

    private void addKsiazka(Scanner scanner) {
        System.out.print("Podaj tytuł: ");
        String tytul = scanner.nextLine();

        System.out.print("Podaj autora: ");
        String autor = scanner.nextLine();

        System.out.print("Podaj ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Podaj liczbę stron: ");
        int liczbaStron = Integer.parseInt(scanner.nextLine());

        System.out.print("Podaj ID biblioteki (UUID): ");
        String bibliotekaId = scanner.nextLine();

        Ksiazka ksiazka = new Ksiazka();
        ksiazka.setId(UUID.randomUUID());
        ksiazka.setTytul(tytul);
        ksiazka.setAutor(autor);
        ksiazka.setIsbn(isbn);
        ksiazka.setLiczbaStron(liczbaStron);
        ksiazka.setBibliotekaId(UUID.fromString(bibliotekaId));

        ksiazkaService.save(ksiazka);
        System.out.println("Książka została dodana.");
    }

    private void deleteKsiazka(Scanner scanner) {
        displayKsiazki();
        System.out.print("Podaj ID książki do usunięcia: ");
        String ksiazkaId = scanner.nextLine();

        ksiazkaService.deleteById(UUID.fromString(ksiazkaId));
        System.out.println("Książka została usunięta.");
    }
}
