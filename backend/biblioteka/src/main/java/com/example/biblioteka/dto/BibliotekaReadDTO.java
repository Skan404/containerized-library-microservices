package com.example.biblioteka.dto;

import java.util.Objects;
import java.util.UUID;

public class BibliotekaReadDTO {
    private UUID id;
    private String nazwa;
    private String lokalizacja;

    // Konstruktor bezargumentowy
    public BibliotekaReadDTO() {
    }

    // Konstruktor z wszystkimi polami
    public BibliotekaReadDTO(UUID id, String nazwa, String lokalizacja) {
        this.id = id;
        this.nazwa = nazwa;
        this.lokalizacja = lokalizacja;
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

    public String getLokalizacja() {
        return lokalizacja;
    }

    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }

    // Metoda toString
    @Override
    public String toString() {
        return "BibliotekaReadDTO{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", lokalizacja='" + lokalizacja + '\'' +
                '}';
    }

    // Metody equals i hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibliotekaReadDTO that = (BibliotekaReadDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nazwa, that.nazwa) &&
                Objects.equals(lokalizacja, that.lokalizacja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazwa, lokalizacja);
    }
}
