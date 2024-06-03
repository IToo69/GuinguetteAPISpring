package com.example.guinguette.controller;

import com.example.guinguette.dto.PichetDto;
import com.example.guinguette.entities.Pichet;
import com.example.guinguette.services.PersonneService;
import com.example.guinguette.services.PichetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/guinguette/pichets")
public class PichetController {

    @Autowired
    private PichetService pichetService;


    @Autowired
    private PersonneService personneService;

    @GetMapping
    public List<Pichet> getAllPichets() {
        return pichetService.getAllPichets();
    }

    @GetMapping("/{id}")
    public Pichet getPichetById(@PathVariable Long id) {
        return pichetService.getPichetById(id);
    }

    @PostMapping
    public Pichet createPichet(@RequestBody PichetDto pichetDto) {
        Pichet newPichet = new Pichet();
        newPichet.setNom(pichetDto.getNom());
        newPichet.setPrix(pichetDto.getPrix());
        newPichet.setPersonne(personneService.getPersonneById(pichetDto.getPersonneId()));
        

        return pichetService.createPichet(newPichet);
    }

    @PutMapping("/{id}")
    public Pichet updatePichet(@PathVariable Long id, @RequestBody PichetDto pichetDto) {
        Pichet existingPichet = pichetService.getPichetById(id);
        if(pichetDto.getNom() != null){
            existingPichet.setNom(pichetDto.getNom());
        }
        if(pichetDto.getPrix() != null){
            existingPichet.setPrix(pichetDto.getPrix());
        }

        if(pichetDto.getPersonneId() != null){
            existingPichet.setPersonne(personneService.getPersonneById(pichetDto.getId()));
        }

        return pichetService.updatePichet(id, existingPichet);
    }

    @DeleteMapping("/{id}")
    public void deletePichet(@PathVariable Long id) {
        pichetService.deletePichet(id);
    }

    @GetMapping("/total-amount")
    public ResponseEntity<Double> getMontantTotalPichets() {
        return ResponseEntity.ok(pichetService.getTotalAmount());
    }

    @GetMapping("/total-count")
    public ResponseEntity<Long> getNombreTotalPichets() {
        return ResponseEntity.ok(pichetService.getNbPichets());
    }

    
    //Retourne dans l'ordre les jours de la semaine où le plus de pichets sont vendus
    @GetMapping("/count-by-day-of-week")
    public List<String> getPichetCountByDayOfWeekOrdered() {
        List<String> pichetDays = pichetService.getPichetCountByDayOfWeekOrdered();
        
        List<String> pichetDaysInFrench = pichetDays.stream()
                .map(this::translateDayNameToFrench)
                .collect(Collectors.toList());

        return pichetDaysInFrench;
    }

    private String translateDayNameToFrench(String dayName) {
        switch (dayName) {
            case "Monday":
                return "Lundi";
            case "Tuesday":
                return "Mardi";
            case "Wednesday":
                return "Mercredi";
            case "Thursday":
                return "Jeudi";
            case "Friday":
                return "Vendredi";
            case "Saturday":
                return "Samedi";
            case "Sunday":
                return "Dimanche";
            default:
                return dayName; 
        }
    }

    @GetMapping("/top5DaysWithMostPichets")
    public List<LocalDate> getTop5DaysWithMostPichets() {
        return pichetService.getTop5DaysWithMostPichets();
    }
    

}