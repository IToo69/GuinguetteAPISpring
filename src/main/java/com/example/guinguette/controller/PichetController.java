package com.example.guinguette.controller;

import com.example.guinguette.dto.PichetDto;
import com.example.guinguette.entities.Pichet;
import com.example.guinguette.services.PersonneService;
import com.example.guinguette.services.PichetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/totalamount")
    public ResponseEntity<Double> getMontantTotalPichets() {
        return ResponseEntity.ok(pichetService.getTotalAmount());
    }

    @GetMapping("/totalcount")
    public ResponseEntity<Long> getNombreTotalPichets() {
        return ResponseEntity.ok(pichetService.getNbPichets());
    }

}