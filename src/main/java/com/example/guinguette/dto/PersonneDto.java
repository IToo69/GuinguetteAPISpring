package com.example.guinguette.dto;

import org.hibernate.mapping.List;

public class PersonneDto {
    private Long id;
    private String nom;
    private String prenom;
    private Double degre;

    // Constructeur par défaut
    public PersonneDto() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Double getDegreAlcool() {
        return degre;
    }

    public void setDegreAlcool(Double degre) {
        this.degre = degre;
    }
}