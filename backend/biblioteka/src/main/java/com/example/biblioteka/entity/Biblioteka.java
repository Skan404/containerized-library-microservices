package com.example.biblioteka.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "biblioteki")
public class Biblioteka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatyczne generowanie UUID
    private UUID id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "lokalizacja")
    private String lokalizacja;

    // Konstruktor bezargumentowy
    public Biblioteka() {
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

    @Override
    public String toString() {
        return "Biblioteka{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", lokalizacja='" + lokalizacja + '\'' +
                '}';
    }
}
