package com.example.guinguette.services;

import com.example.guinguette.entities.Personne;
import com.example.guinguette.exception.ResourceNotFoundException;
import com.example.guinguette.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    public List<Personne> getAllPersonnesOrderedByNom() {
        return personneRepository.orderedByNom();
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

    public List<Personne> getTop5PersonnesWithMostPichets() {
        List<Object[]> results = personneRepository.findTop5PersonnesWithMostPichets();
        return results.stream()
                      .map(result -> (Personne) result[0])
                      .collect(Collectors.toList());
    }

    public List<Personne> getTop5PersonnesWithMostPichetsToday() {
        List<Object[]> results = personneRepository.findTop5PersonnesWithMostPichetsToday();
        return results.stream()
                      .map(result -> (Personne) result[0])
                      .collect(Collectors.toList());
    }

    public List<Personne> getTop5PersonnesWithMostPichetsThisWeek() {
        List<Object[]> results = personneRepository.findTop5PersonnesWithMostPichetsThisWeek();
        return results.stream()
                      .map(result -> (Personne) result[0])
                      .collect(Collectors.toList());
    }

    public List<Personne> getTop5PersonnesWithMostPichetsThisMonth() {
        List<Object[]> results = personneRepository.findTop5PersonnesWithMostPichetsThisMonth();
        return results.stream()
                      .map(result -> (Personne) result[0])
                      .collect(Collectors.toList());
    }

    public List<Personne> getTop5PersonnesWithMostPichetsThisYear() {
        List<Object[]> results = personneRepository.findTop5PersonnesWithMostPichetsThisYear();
        return results.stream()
                      .map(result -> (Personne) result[0])
                      .collect(Collectors.toList());
    }

    
}