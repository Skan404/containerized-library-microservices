package com.example.biblioteka.repository;

import com.example.biblioteka.entity.Biblioteka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BibliotekaRepository extends JpaRepository<Biblioteka, UUID> {
    //
}
