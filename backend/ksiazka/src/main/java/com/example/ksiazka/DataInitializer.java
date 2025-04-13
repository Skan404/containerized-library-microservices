package com.example.ksiazka;

import com.example.ksiazka.entity.Ksiazka;
import com.example.ksiazka.service.KsiazkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class DataInitializer {

  private final KsiazkaService ksiazkaService;
  private final RestTemplate restTemplate;

  @Autowired
  public DataInitializer(KsiazkaService ksiazkaService, RestTemplate restTemplate) {
    this.ksiazkaService = ksiazkaService;
    this.restTemplate = restTemplate;
  }

  @PostConstruct
  public void init() {
    // Pobierz wszystkie biblioteki z mikroserwisu
    String bibliotekaServiceUrl = "http://localhost:8080/api/biblioteka";
    List<Map<String, Object>> biblioteki;

    try {
      biblioteki = restTemplate.getForObject(bibliotekaServiceUrl, List.class);
    } catch (Exception e) {
      System.err.println("Nie udało się pobrać bibliotek: " + e.getMessage());
      return;
    }

    if (biblioteki == null || biblioteki.isEmpty()) {
      System.err.println("Brak bibliotek do inicjalizacji książek.");
      return;
    }

    // Wybierz pierwsze dwie biblioteki
    UUID biblioteka1Id = UUID.fromString((String) biblioteki.get(0).get("id"));
    UUID biblioteka2Id = biblioteki.size() > 1 ? UUID.fromString((String) biblioteki.get(1).get("id")) : biblioteka1Id;

    // Tworzenie przykładowych książek
    Ksiazka ksiazka1 = new Ksiazka();
    ksiazka1.setId(UUID.randomUUID());
    ksiazka1.setTytul("Pan Tadeusz");
    ksiazka1.setAutor("Adam Mickiewicz");
    ksiazka1.setIsbn("978-83-01-00000-1");
    ksiazka1.setLiczbaStron(340);
    ksiazka1.setBibliotekaId(biblioteka1Id);
    ksiazkaService.save(ksiazka1);

    Ksiazka ksiazka2 = new Ksiazka();
    ksiazka2.setId(UUID.randomUUID());
    ksiazka2.setTytul("Lalka");
    ksiazka2.setAutor("Bolesław Prus");
    ksiazka2.setIsbn("978-83-01-00000-2");
    ksiazka2.setLiczbaStron(500);
    ksiazka2.setBibliotekaId(biblioteka1Id);
    ksiazkaService.save(ksiazka2);

    Ksiazka ksiazka3 = new Ksiazka();
    ksiazka3.setId(UUID.randomUUID());
    ksiazka3.setTytul("Zbrodnia i Kara");
    ksiazka3.setAutor("Fiodor Dostojewski");
    ksiazka3.setIsbn("652-43-12-12341-6");
    ksiazka3.setLiczbaStron(750);
    ksiazka3.setBibliotekaId(biblioteka2Id);
    ksiazkaService.save(ksiazka3);

    System.out.println("Dane początkowe zostały załadowane.");
  }
}
