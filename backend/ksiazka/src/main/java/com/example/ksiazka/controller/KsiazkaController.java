package com.example.ksiazka.controller;

import com.example.ksiazka.dto.*;
import com.example.ksiazka.entity.Ksiazka;
import com.example.ksiazka.service.KsiazkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ksiazki")
public class KsiazkaController {

    private final KsiazkaService ksiazkaService;
    private final RestTemplate restTemplate;

    @Autowired
    public KsiazkaController(KsiazkaService ksiazkaService, RestTemplate restTemplate) {
        this.ksiazkaService = ksiazkaService;
        this.restTemplate = restTemplate;
    }

    // Pobierz wszystkie książki
    @GetMapping
    public ResponseEntity<List<KsiazkaCollectionDTO>> getAllKsiazki() {
        List<Ksiazka> ksiazki = ksiazkaService.findAll();
        List<KsiazkaCollectionDTO> dtoList = ksiazki.stream()
                .map(this::mapToKsiazkaCollectionDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // Pobierz książki dla konkretnej biblioteki
    @GetMapping("/biblioteka/{bibliotekaId}")
    public ResponseEntity<List<KsiazkaCollectionDTO>> getAllKsiazkiByBibliotekaId(@PathVariable UUID bibliotekaId) {
        List<Ksiazka> ksiazki = ksiazkaService.findByBibliotekaId(bibliotekaId);
        List<KsiazkaCollectionDTO> dtoList = ksiazki.stream()
                .map(this::mapToKsiazkaCollectionDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // Pobierz szczegóły książki
    @GetMapping("/{id}")
    public ResponseEntity<KsiazkaReadDTO> getKsiazkaById(@PathVariable UUID id) {
        Ksiazka ksiazka = ksiazkaService.findById(id);
        if (ksiazka != null) {
            return ResponseEntity.ok(mapToKsiazkaReadDTO(ksiazka));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

  // Dodaj nową książkę
  @PostMapping
  public ResponseEntity<KsiazkaReadDTO> createKsiazka(@RequestBody KsiazkaCreateUpdateDTO dto) {
    // Sprawdź, czy ID biblioteki istnieje
    String bibliotekaServiceUrl = "http://localhost:8080/api/biblioteka/" + dto.getBibliotekaId();
    try {
      restTemplate.getForObject(bibliotekaServiceUrl, Void.class);
    } catch (Exception e) {
      System.err.println("Biblioteka o ID " + dto.getBibliotekaId() + " nie istnieje: " + e.getMessage());
      return ResponseEntity.badRequest().build();
    }

    // Tworzenie książki
    Ksiazka ksiazka = new Ksiazka();
    ksiazka.setId(UUID.randomUUID());
    ksiazka.setTytul(dto.getTytul());
    ksiazka.setAutor(dto.getAutor());
    ksiazka.setIsbn(dto.getIsbn());
    ksiazka.setLiczbaStron(dto.getLiczbaStron());
    ksiazka.setBibliotekaId(dto.getBibliotekaId());

    Ksiazka savedKsiazka = ksiazkaService.save(ksiazka);
    return ResponseEntity.status(201).body(mapToKsiazkaReadDTO(savedKsiazka));
  }
  @PostMapping("/biblioteka")
  public ResponseEntity<Void> handleNewBiblioteka(@RequestBody Map<String, String> payload) {
    try {
      String bibliotekaId = payload.get("bibliotekaId");
      String nazwa = payload.get("nazwa");

      if (bibliotekaId == null || nazwa == null) {
        return ResponseEntity.badRequest().build();
      }

      System.out.println("Otrzymano powiadomienie o nowej bibliotece: ID = " + bibliotekaId + ", Nazwa = " + nazwa);
      return ResponseEntity.status(201).build();
    } catch (Exception e) {
      System.err.println("Błąd podczas obsługi nowej biblioteki: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }




    // Zaktualizuj istniejącą książkę
    @PutMapping("/{id}")
    public ResponseEntity<KsiazkaReadDTO> updateKsiazka(@PathVariable UUID id, @RequestBody KsiazkaCreateUpdateDTO dto) {
        Ksiazka ksiazka = ksiazkaService.findById(id);
        if (ksiazka != null) {
            ksiazka.setTytul(dto.getTytul());
            ksiazka.setAutor(dto.getAutor());
            ksiazka.setIsbn(dto.getIsbn());
            ksiazka.setLiczbaStron(dto.getLiczbaStron());
            ksiazka.setBibliotekaId(dto.getBibliotekaId());

            if (dto.getBibliotekaId() != null) {
                ksiazka.setBibliotekaId(dto.getBibliotekaId());
            } else {
                return ResponseEntity.badRequest().body(null);
            }

            Ksiazka updatedKsiazka = ksiazkaService.save(ksiazka);
            return ResponseEntity.ok(mapToKsiazkaReadDTO(updatedKsiazka));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Usuń książkę
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKsiazka(@PathVariable UUID id) {
        Ksiazka ksiazka = ksiazkaService.findById(id);
        if (ksiazka != null) {
            ksiazkaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Usuń wszystkie książki w bibliotece
    @DeleteMapping("/biblioteka/{bibliotekaId}")
    public ResponseEntity<Void> deleteKsiazkiByBibliotekaId(@PathVariable UUID bibliotekaId) {
        List<Ksiazka> ksiazki = ksiazkaService.findByBibliotekaId(bibliotekaId);
        ksiazki.forEach(ksiazka -> ksiazkaService.deleteById(ksiazka.getId()));
        return ResponseEntity.noContent().build();
    }


    // Metody pomocnicze do mapowania
    private KsiazkaCollectionDTO mapToKsiazkaCollectionDTO(Ksiazka ksiazka) {
        KsiazkaCollectionDTO dto = new KsiazkaCollectionDTO();
        dto.setId(ksiazka.getId());
        dto.setTytul(ksiazka.getTytul());
        return dto;
    }

    private KsiazkaReadDTO mapToKsiazkaReadDTO(Ksiazka ksiazka) {
        KsiazkaReadDTO dto = new KsiazkaReadDTO();
        dto.setId(ksiazka.getId());
        dto.setTytul(ksiazka.getTytul());
        dto.setAutor(ksiazka.getAutor());
        dto.setIsbn(ksiazka.getIsbn());
        dto.setLiczbaStron(ksiazka.getLiczbaStron());
        dto.setBibliotekaId(ksiazka.getBibliotekaId());
        return dto;
    }
}
