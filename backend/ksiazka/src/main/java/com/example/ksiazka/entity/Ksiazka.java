package com.example.ksiazka.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ksiazki")
public class Ksiazka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatyczne generowanie UUID
    private UUID id;

    @Column(name = "tytul")
    private String tytul;

    @Column(name = "autor")
    private String autor;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "liczba_stron")
    private int liczbaStron;

    @Column(name = "biblioteka_id")
    private UUID bibliotekaId;

    public Ksiazka() {
    }

    public Ksiazka(UUID id, String tytul, String autor, String isbn, int liczbaStron, UUID bibliotekaId) {
        this.id = id;
        this.tytul = tytul;
        this.autor = autor;
        this.isbn = isbn;
        this.liczbaStron = liczbaStron;
        this.bibliotekaId = bibliotekaId;
    }

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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getLiczbaStron() {
        return liczbaStron;
    }

    public void setLiczbaStron(int liczbaStron) {
        this.liczbaStron = liczbaStron;
    }

    public UUID getBibliotekaId() {
        return bibliotekaId;
    }

    public void setBibliotekaId(UUID bibliotekaId) {
        this.bibliotekaId = bibliotekaId;
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", tytul='" + tytul + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", liczbaStron=" + liczbaStron +
                ", bibliotekaId=" + bibliotekaId +
                '}';
    }
}
