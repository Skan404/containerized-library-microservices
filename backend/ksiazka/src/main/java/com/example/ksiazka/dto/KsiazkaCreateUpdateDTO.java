package com.example.ksiazka.dto;

import java.util.UUID;

public class KsiazkaCreateUpdateDTO {

    private String tytul;
    private String autor;
    private String isbn;
    private int liczbaStron;
    private UUID bibliotekaId;

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
}
