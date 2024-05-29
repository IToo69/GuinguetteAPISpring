package com.example.guinguette.entities;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Pichet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Long prix;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "personne_id")
    @JsonBackReference
    private Personne personne;

    public Pichet() {
        nom = "N/A";
        date = LocalDateTime.now();
    }

    public Pichet(Long id, String nom, Personne personne) {
        this.id = id;
        this.nom = nom;
        this.personne = personne;
        date = LocalDateTime.now();
    }
}