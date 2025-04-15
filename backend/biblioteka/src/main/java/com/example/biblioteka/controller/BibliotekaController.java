package com.example.biblioteka.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.biblioteka.dto.BibliotekaCollectionDTO;
import com.example.biblioteka.dto.BibliotekaCreateUpdateDTO;
import com.example.biblioteka.dto.BibliotekaReadDTO;
import com.example.biblioteka.entity.Biblioteka;
import com.example.biblioteka.service.BibliotekaService;

@RestController
@RequestMapping("/api/biblioteka")
public class BibliotekaController {

    private final BibliotekaService bibliotekaService;
    private final RestTemplate restTemplate;

    @Autowired
    public BibliotekaController(BibliotekaService bibliotekaService, RestTemplate restTemplate) {
        this.bibliotekaService = bibliotekaService;
        this.restTemplate = restTemplate;
    }

    // 2.1.1. Pobierz wszystkie biblioteki
    @GetMapping
    public ResponseEntity<List<BibliotekaCollectionDTO>> getAllBiblioteki() {
        List<Biblioteka> biblioteki = bibliotekaService.findAll();
        List<BibliotekaCollectionDTO> dtoList = biblioteki.stream()
                .map(this::mapToBibliotekaCollectionDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // 2.1.2. Pobierz pojedynczą bibliotekę
    @GetMapping("/{id}")
    public ResponseEntity<BibliotekaReadDTO> getBibliotekaById(@PathVariable UUID id) {
        Biblioteka biblioteka = bibliotekaService.findById(id);
        if (biblioteka != null) {
            return ResponseEntity.ok(mapToBibliotekaReadDTO(biblioteka));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
/*
    // 2.1.3. Utwórz nową bibliotekę
    @PostMapping
    public ResponseEntity<BibliotekaReadDTO> createBiblioteka(@RequestBody BibliotekaCreateUpdateDTO dto) {
        Biblioteka biblioteka = new Biblioteka();
        biblioteka.setId(UUID.randomUUID());
        biblioteka.setNazwa(dto.getNazwa());
        biblioteka.setLokalizacja(dto.getLokalizacja());
        Biblioteka savedBiblioteka = bibliotekaService.save(biblioteka);
        return ResponseEntity.status(201).body(mapToBibliotekaReadDTO(savedBiblioteka));
    }
*/
    @PostMapping
    public ResponseEntity<BibliotekaReadDTO> createBiblioteka(@RequestBody BibliotekaCreateUpdateDTO dto) {
      Biblioteka biblioteka = new Biblioteka();
      biblioteka.setId(UUID.randomUUID());
      biblioteka.setNazwa(dto.getNazwa());
      biblioteka.setLokalizacja(dto.getLokalizacja());
      Biblioteka savedBiblioteka = bibliotekaService.save(biblioteka);

      // Powiadomienie o nowej bibliotece
      String ksiazkaServiceUrl = "http://localhost:8081/api/ksiazki/biblioteka";
      Map<String, String> payload = Map.of(
        "bibliotekaId", savedBiblioteka.getId().toString(),
        "nazwa", savedBiblioteka.getNazwa()
      );

      try {
        restTemplate.postForObject(ksiazkaServiceUrl, payload, Void.class);
      } catch (Exception e) {
        System.err.println("Błąd podczas powiadamiania KsiazkaService: " + e.getMessage());
      }

      return ResponseEntity.status(201).body(mapToBibliotekaReadDTO(savedBiblioteka));
    }



    // 2.1.4. Aktualizuj istniejącą bibliotekę
    @PutMapping("/{id}")
    public ResponseEntity<BibliotekaReadDTO> updateBiblioteka(@PathVariable UUID id, @RequestBody BibliotekaCreateUpdateDTO dto) {
        Biblioteka biblioteka = bibliotekaService.findById(id);
        if (biblioteka != null) {
            biblioteka.setNazwa(dto.getNazwa());
            biblioteka.setLokalizacja(dto.getLokalizacja());
            Biblioteka updatedBiblioteka = bibliotekaService.save(biblioteka);
            return ResponseEntity.ok(mapToBibliotekaReadDTO(updatedBiblioteka));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 2.1.5. Usuń bibliotekę wraz z powiązanymi książkami


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBiblioteka(@PathVariable UUID id) {
    Biblioteka biblioteka = bibliotekaService.findById(id);
    if (biblioteka != null) {
      bibliotekaService.deleteById(id);

      // wysylanie przez ggateway
      String ksiazkaServiceUrl = "http://localhost:8080/api/ksiazki/biblioteka/" + id;
      try {
        restTemplate.delete(ksiazkaServiceUrl);
      } catch (Exception e) {
        System.err.println("Błąd podczas usuwania książek dla biblioteki: " + e.getMessage());
      }

      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}/elements")
  public ResponseEntity<List<Map<String, Object>>> getElementsByCategory(@PathVariable UUID id) {
    Biblioteka biblioteka = bibliotekaService.findById(id);
    if (biblioteka != null) {
      String ksiazkaServiceUrl = "http://localhost:8080/api/ksiazki/biblioteka/" + id;

      try {
        List<Map<String, Object>> elements = restTemplate.getForObject(ksiazkaServiceUrl, List.class);
        return ResponseEntity.ok(elements);
      } catch (Exception e) {
        System.err.println("Błąd podczas pobierania elementów dla kategorii: " + e.getMessage());
        return ResponseEntity.status(500).build();
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }



  // Metody pomocnicze do mapowania
    private BibliotekaCollectionDTO mapToBibliotekaCollectionDTO(Biblioteka biblioteka) {
        BibliotekaCollectionDTO dto = new BibliotekaCollectionDTO();
        dto.setId(biblioteka.getId());
        dto.setNazwa(biblioteka.getNazwa());
        return dto;
    }

    private BibliotekaReadDTO mapToBibliotekaReadDTO(Biblioteka biblioteka) {
        BibliotekaReadDTO dto = new BibliotekaReadDTO();
        dto.setId(biblioteka.getId());
        dto.setNazwa(biblioteka.getNazwa());
        dto.setLokalizacja(biblioteka.getLokalizacja());
        return dto;
    }
}
