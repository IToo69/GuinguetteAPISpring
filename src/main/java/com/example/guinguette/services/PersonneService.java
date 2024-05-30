package com.example.guinguette.services;

import com.example.guinguette.entities.Personne;
import com.example.guinguette.exception.ResourceNotFoundException;
import com.example.guinguette.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    public Personne getPersonneById(Long id) {
        return personneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Personne not found"));
    }

    public Personne createPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public Personne updatePersonne(Long id, Personne personneDetails) {
        Personne personne = personneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Personne not found"));

        personne.setNom(personneDetails.getNom());
        personne.setPrenom(personneDetails.getPrenom());

        return personneRepository.save(personne);
    }

    public void deletePersonne(Long id) {
        Personne personne = personneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Personne not found"));
        personneRepository.delete(personne);
    }

    public long getTotalNumberOfPersonnes() {
        return personneRepository.countPersonnes();
    }

    public Long countPichetsByPersonneId(Long personneId) {
        return personneRepository.countPichetsByPersonneId(personneId);
    }

    public Long getAmountByPersonneId(Long personneId) {
        return personneRepository.sumAmountByPersonneId(personneId);
    }
}