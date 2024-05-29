package com.example.guinguette.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PichetDto {
    private Long id;
    private String nom;
    private Long prix;
    private LocalDateTime date;
    private Long personneId;

    // Constructeur par défaut
    public PichetDto() {
        date = LocalDateTime.now();
    }

    // Getters et setters pour chaque attribut
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getPersonneId() {
        return personneId;
    }

    public void setPersonneId(Long personneId) {
        this.personneId = personneId;
    }

    public Long getPrix(){
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        this.date = dateTime;
    }


}