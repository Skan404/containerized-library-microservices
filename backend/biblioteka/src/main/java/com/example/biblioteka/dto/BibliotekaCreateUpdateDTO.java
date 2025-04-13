package com.example.biblioteka.dto;

import java.util.Objects;

public class BibliotekaCreateUpdateDTO {
    private String nazwa;

    private String lokalizacja;

    // Konstruktor bezargumentowy
    public BibliotekaCreateUpdateDTO() {
    }

    // Konstruktor z wszystkimi polami
    public BibliotekaCreateUpdateDTO(String nazwa, String lokalizacja) {
        this.nazwa = nazwa;
        this.lokalizacja = lokalizacja;
    }

    // Gettery i settery
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
        return "BibliotekaCreateUpdateDTO{" +
                "nazwa='" + nazwa + '\'' +
                ", lokalizacja='" + lokalizacja + '\'' +
                '}';
    }

    // Metody equals i hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibliotekaCreateUpdateDTO that = (BibliotekaCreateUpdateDTO) o;
        return nazwa.equals(that.nazwa) && lokalizacja.equals(that.lokalizacja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa, lokalizacja);
    }
}
