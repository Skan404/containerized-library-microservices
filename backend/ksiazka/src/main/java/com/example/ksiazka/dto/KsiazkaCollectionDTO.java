package com.example.ksiazka.dto;

import java.util.Objects;
import java.util.UUID;

public class KsiazkaCollectionDTO {
    private UUID id;
    private String tytul;

    // Konstruktor bezargumentowy
    public KsiazkaCollectionDTO() {
    }

    // Konstruktor z wszystkimi polami
    public KsiazkaCollectionDTO(UUID id, String tytul) {
        this.id = id;
        this.tytul = tytul;
    }

    // Gettery i settery
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    // Metoda toString
    @Override
    public String toString() {
        return "KsiazkaCollectionDTO{" +
                "id=" + id +
                ", tytul='" + tytul + '\'' +
                '}';
    }

    // Metody equals i hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KsiazkaCollectionDTO that = (KsiazkaCollectionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(tytul, that.tytul);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tytul);
    }
}
