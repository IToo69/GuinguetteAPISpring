package com.example.guinguette.services;

import com.example.guinguette.entities.Pichet;
import com.example.guinguette.exception.ResourceNotFoundException;
import com.example.guinguette.repositories.PichetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PichetService {

    @Autowired
    private PichetRepository pichetRepository;

    public List<Pichet> getAllPichets() {
        return pichetRepository.findAll();
    }

    public Pichet getPichetById(Long id) {
        return pichetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pichet not found"));
    }

    public Pichet createPichet(Pichet pichet) {
        return pichetRepository.save(pichet);
    }

    public Pichet updatePichet(Long id, Pichet pichetDetails) {
        Pichet pichet = pichetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pichet not found"));
        if(pichet.getNom() != null ){
            pichet.setNom(pichetDetails.getNom());
        }
        if(pichet.getPersonne() != null ){
            pichet.setPersonne(pichetDetails.getPersonne());
        }
        if(pichet.getPrix() != null ){
            pichet.setPrix(pichetDetails.getPrix());
        }

        return pichetRepository.save(pichet);
    }

    public void deletePichet(Long id) {
        Pichet pichet = pichetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pichet not found"));
        pichetRepository.delete(pichet);
    }

    public Double getTotalAmount() {
        return pichetRepository.sumAmountTotal();
    }

    public Long getNbPichets() {
        return pichetRepository.countPichets();
    }

    
    public List<String> getPichetCountByDayOfWeekOrdered() {
        return pichetRepository.findPichetCountByDayOfWeekOrdered();
    }

    public List<LocalDate> getTop5DaysWithMostPichets() {
        List<Object[]> results = pichetRepository.findTop5DaysWithMostPichets();
        return results.stream()
                      .map(result -> ((java.sql.Date) result[0]).toLocalDate())
                      .collect(Collectors.toList());
    }
    
}