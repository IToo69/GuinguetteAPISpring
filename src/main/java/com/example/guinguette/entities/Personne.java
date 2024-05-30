package com.example.guinguette.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pichet> pichets;

    public Personne() {
        pichets = new ArrayList<>();
    }

    public Personne(Long id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        pichets = new ArrayList<>();
    }

    public void addPichet(Pichet pichet){
        if(!pichets.contains(pichet)){
            pichets.add(pichet);
        }
    }

    public void removePichet(Pichet pichet){
        if(pichets.contains(pichet)){
            pichets.remove(pichet);
        }
    }


}