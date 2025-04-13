package com.example.ksiazka.service;

import com.example.ksiazka.entity.Ksiazka;
import com.example.ksiazka.repository.KsiazkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KsiazkaService {

    private final KsiazkaRepository ksiazkaRepository;

    @Autowired
    public KsiazkaService(KsiazkaRepository ksiazkaRepository) {
        this.ksiazkaRepository = ksiazkaRepository;
    }

    public List<Ksiazka> findAll() {
        return ksiazkaRepository.findAll();
    }

    public Ksiazka findById(UUID id) {
        return ksiazkaRepository.findById(id).orElse(null);
    }

    public Ksiazka save(Ksiazka ksiazka) {
        return ksiazkaRepository.save(ksiazka);
    }

    public void deleteById(UUID id) {
        ksiazkaRepository.deleteById(id);
    }

    public List<Ksiazka> findByBibliotekaId(UUID bibliotekaId) {
        return ksiazkaRepository.findByBibliotekaId(bibliotekaId);
    }
}
