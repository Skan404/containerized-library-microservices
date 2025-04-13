package com.example.biblioteka.dto;

import java.util.Objects;
import java.util.UUID;

public class BibliotekaCollectionDTO {
    private UUID id;
    private String nazwa;

    // Konstruktor bezargumentowy
    public BibliotekaCollectionDTO() {
    }

    // Konstruktor z wszystkimi polami
    public BibliotekaCollectionDTO(UUID id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    // Gettery i settery
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    // Metoda toString
    @Override
    public String toString() {
        return "BibliotekaCollectionDTO{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

    // Metody equals i hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibliotekaCollectionDTO that = (BibliotekaCollectionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nazwa, that.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazwa);
    }
}
