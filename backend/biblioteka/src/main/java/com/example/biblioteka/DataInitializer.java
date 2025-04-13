package com.example.biblioteka;

import com.example.biblioteka.entity.Biblioteka;
import com.example.biblioteka.service.BibliotekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.UUID;

@Component
public class DataInitializer {

    private final BibliotekaService bibliotekaService;

    @Autowired
    public DataInitializer(BibliotekaService bibliotekaService) {
        this.bibliotekaService = bibliotekaService;
    }

    @PostConstruct
    public void init() {
        Biblioteka biblioteka1 = new Biblioteka();
        biblioteka1.setId(UUID.randomUUID());
        biblioteka1.setNazwa("Biblioteka Główna");
        biblioteka1.setLokalizacja("Warszawa");
        bibliotekaService.save(biblioteka1);

        Biblioteka biblioteka2 = new Biblioteka();
        biblioteka2.setId(UUID.randomUUID());
        biblioteka2.setNazwa("Biblioteka Miejska");
        biblioteka2.setLokalizacja("Kraków");
        bibliotekaService.save(biblioteka2);

        Biblioteka biblioteka3 = new Biblioteka();
        biblioteka3.setId(UUID.randomUUID());
        biblioteka3.setNazwa("Biblioteka Gdańska");
        biblioteka3.setLokalizacja("Gdańsk");
        bibliotekaService.save(biblioteka3);

        Biblioteka biblioteka4 = new Biblioteka();
        biblioteka4.setId(UUID.randomUUID());
        biblioteka4.setNazwa("Biblioteka Poznańska");
        biblioteka4.setLokalizacja("Poznań");
        bibliotekaService.save(biblioteka4);

        Biblioteka biblioteka5 = new Biblioteka();
        biblioteka5.setId(UUID.randomUUID());
        biblioteka5.setNazwa("Biblioteka Wrocławska");
        biblioteka5.setLokalizacja("Wrocław");
        bibliotekaService.save(biblioteka5);

        System.out.println("Dane początkowe zostały załadowane.");
    }
}
