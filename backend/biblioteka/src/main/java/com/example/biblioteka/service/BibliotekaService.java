package com.example.biblioteka.service;

import com.example.biblioteka.entity.Biblioteka;
import com.example.biblioteka.repository.BibliotekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BibliotekaService {

    private final BibliotekaRepository bibliotekaRepository;

    @Autowired
    public BibliotekaService(BibliotekaRepository bibliotekaRepository) {
        this.bibliotekaRepository = bibliotekaRepository;
    }

    public List<Biblioteka> findAll() {
        return bibliotekaRepository.findAll();
    }

    public Biblioteka findById(UUID id) {
        return bibliotekaRepository.findById(id).orElse(null);
    }

    public Biblioteka save(Biblioteka biblioteka) {
        return bibliotekaRepository.save(biblioteka);
    }

    public void deleteById(UUID id) {
        bibliotekaRepository.deleteById(id);
    }
}
