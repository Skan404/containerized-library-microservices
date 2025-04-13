package com.example.ksiazka.repository;

import com.example.ksiazka.entity.Ksiazka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KsiazkaRepository extends JpaRepository<Ksiazka, UUID> {

    // Wyszukiwanie książek po ID biblioteki
    List<Ksiazka> findByBibliotekaId(UUID bibliotekaId);
}
